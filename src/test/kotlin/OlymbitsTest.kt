import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OlymbitsTest {

//    mini_game_score = nb_silver_medals + nb_gold_medals * 3
//    The scores for all four mini-games are multiplied together to determine the final score.
//
//    G S B
//    0 6 0 => 6
//    2 2 0 => 8
//    1 1 2 => 4
//    2 2 0 => 8
//    => 1536

    private val olymbits1 = Olymbits(
        RaceData(1, 0)
    )

    @Test
    fun should_collect_game_data() {
        // arrange

        // act
        val raceData = RaceData(2, 0)

        // assert
        assertThat(raceData.numberOfGames).isEqualTo(2)
        assertThat(raceData.playerIndex).isEqualTo(0)
    }

    @Test
    fun should_collect_turn_data() {
        // act
        val turnData = TurnData(
            ScoreInfo("100 1 2 3 4 5 6 7 8 9 10 11 12"),
            ScoreInfo("200 4 5 6 7 8 9 10 11 12 13 14 15"),
            ScoreInfo("300 7 8 9 10 11 12 13 14 15 16 17 18"),
        )

        // assert
        assertThat(turnData.scoreInfoPlayer1.finalScore).isEqualTo(100)
        assertThat(turnData.scoreInfoPlayer1.miniGames[0].gold).isEqualTo(1)
        assertThat(turnData.scoreInfoPlayer1.miniGames[0].silver).isEqualTo(2)
        assertThat(turnData.scoreInfoPlayer1.miniGames[0].bronze).isEqualTo(3)
    }

    @Test
    fun should_add_game_data() {
        // arrange
        val turnData = TurnData(
            ScoreInfo("100 1 2 3"),
            ScoreInfo("200 4 5 6"),
            ScoreInfo("300 7 8 9"),
        )

        // act
        turnData.addGameData(
            GameData(
                ".....#...#...#....",
                0,
                6,
                12,
                1,
                0,
                2,
                -1
            )
        )

        // assert
        val gameData = turnData.games[0]
        assertThat(gameData.raceTrack).isEqualTo(".....#...#...#....")
        assertThat(gameData.positionPlayer1).isEqualTo(0)
        assertThat(gameData.positionPlayer2).isEqualTo(6)
        assertThat(gameData.positionPlayer3).isEqualTo(12)
        assertThat(gameData.stunTimerPlayer1).isEqualTo(1)
        assertThat(gameData.stunTimerPlayer2).isEqualTo(0)
        assertThat(gameData.stunTimerPlayer3).isEqualTo(2)
    }

    @Test
    fun should_return_0_when_the_distance_to_the_hurtle_is_0() {
        // arrange

        // act
        val distanceToHurtle = olymbits1.distanceToHurtle(".#", 0)

        // assert
        assertThat(distanceToHurtle).isEqualTo(0)
    }

    @Test
    fun should_return_1_when_the_distance_to_the_hurtle_is_1() {
        // arrange

        // act
        val distanceToHurtle = olymbits1.distanceToHurtle("..#", 0)

        // assert
        assertThat(distanceToHurtle).isEqualTo(1)
    }

    @Test
    fun should_return_5_when_the_distance_to_the_hurtle_is_5() {

        // act
        val distanceToHurtle = olymbits1.distanceToHurtle("......#", 0)

        // assert
        assertThat(distanceToHurtle).isEqualTo(5)
    }

    @Test
    fun should_return_3_when_the_player_is_at_position_2_and_the_distance_from_the_start_to_the_hurtle_is_5() {
        // arrange

        // act
        val distanceToHurtle = olymbits1.distanceToHurtle("......#", 2)

        // assert
        assertThat(distanceToHurtle).isEqualTo(3)
    }

    @Test
    fun should_press_RIGHGT_when_hurtle_is_5_steps_away() {
        // act
        val keyCommand = olymbits1.keyCommand(5)

        // assert
        assertThat(keyCommand).isEqualTo("RIGHT")
    }

    @Test
    fun should_press_DOWN_when_hurtle_is_2_steps_away() {
        // act
        val keyCommand = olymbits1.keyCommand(2)

        // assert
        assertThat(keyCommand).isEqualTo("DOWN")
    }

    @Test
    fun should_press_LEFT_when_hurtle_is_1_steps_away() {
        // act
        val keyCommand = olymbits1.keyCommand(1)

        // assert
        assertThat(keyCommand).isEqualTo("LEFT")
    }

    @Test
    fun should_press_UP_when_hurtle_is_0_steps_away() {
        // act
        val keyCommand = olymbits1.keyCommand(0)

        // assert
        assertThat(keyCommand).isEqualTo("UP")
    }

    @Test
    fun should_return_DOWN_when_the_hurtle_is_2_steps_apart() {
        // arrange
        val turnData = TurnData(
            ScoreInfo("100 1 2 3"),
            ScoreInfo("200 4 5 6"),
            ScoreInfo("300 7 8 9"),
        )
        turnData.addGameData(
            GameData(
                "...#...#....",
                0,
                6,
                12,
                0,
                0,
                0,
                -1
            )
        )

        // act
        val output = olymbits1.playTurn(turnData)

        // assert
        assertThat(output).isEqualTo("DOWN")
    }

    @Test
    fun should_create_Score_for_each_mini_game() {

        // act
        val scoreInfo = ScoreInfo("100 1 2 3 4 5 6 7 8 9 10 11 12")

        // assert
        assertThat(scoreInfo.finalScore).isEqualTo(100)
        assertThat(scoreInfo.miniGames[0].gold).isEqualTo(1)
        assertThat(scoreInfo.miniGames[1].gold).isEqualTo(4)
        assertThat(scoreInfo.miniGames[2].gold).isEqualTo(7)
        assertThat(scoreInfo.miniGames[3].gold).isEqualTo(10)
    }

    @Test
    fun should_return_index_of_first_game_when_all_games_have_same_number_of_medals() {
        // arrange
        val turnData = TurnData(
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
        )

        // act
        val index = turnData.indexOfGameWithLeastMedals(1)

        // assert
        assertThat(index).isEqualTo(0)
    }

    @Test
    fun should_return_index_of_last_game_when_other_games_have_more_medals() {
        // arrange
        val turnData = TurnData(
            ScoreInfo("0 1 0 0 1 0 0 1 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
        )
        // act
        val index = turnData.indexOfGameWithLeastMedals(0)

        // assert
        assertThat(index).isEqualTo(3)
    }


    @Test
    fun should_count_number_of_hurdles() {
        // arrange
        val gameData = GameData(
            ".....#...#...#....",
            0,
            6,
            12,
            1,
            0,
            2,
            -1
        )
        // act
        val hurdles = gameData.countGameHurdles()

        // assert
        assertEquals(3, hurdles)
    }

    @Test
    fun should_return_player_of_given_index() {
        // arrange
        val gameData = GameData(
            ".....#...#...#....",
            0,
            6,
            12,
            1,
            0,
            2,
            -1
        )

        // act
        val playerPosition = gameData.getPlayerPosition(1)

        // assert
        assertThat(playerPosition).isEqualTo(6)
    }

    @Test
    fun should_return_index_of_game_with_least_hurdles() {
        // arrange
        val turnData = TurnData(
            ScoreInfo("0 1 0 0 1 0 0 1 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
        )
        turnData.addGameData(
            GameData(
                ".....#...#...#....",
                0,
                6,
                12,
                1,
                0,
                2,
                -1
            )
        )
        turnData.addGameData(
            GameData(
                ".....#...###.#....",
                0,
                6,
                12,
                1,
                0,
                2,
                -1
            )
        )

        // act
        var indexOfGame = turnData.getIndexOfGameWithLeastHurdles()

        // assert
        assertThat(indexOfGame).isEqualTo(0)

    }

    @Test
    fun should_stay_in_current_game_till_game_over() {

        // arrange
        val olymbits = Olymbits(RaceData(2, 1))

        val turnData = TurnData(
            ScoreInfo("0 1 0 0 1 0 0 1 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
        )
        turnData.addGameData(
            GameData(
                ".....#...#...#....",
                0,
                6,
                12,
                1,
                0,
                2,
                -1
            )
        )
        turnData.addGameData(
            GameData(
                "GAME_OVER",
                0,
                6,
                12,
                1,
                0,
                2,
                -1
            )
        )

        // act
        olymbits.determineCurrentGame("GAME_OVER", turnData)

        // asserrt
        assertThat(olymbits.raceData.currentGame).isEqualTo(0)
    }

    @Test
    fun should_return_race_with_best_postion() {
        // arrange
        val turnData = TurnData(
            ScoreInfo("0 1 0 0 1 0 0 1 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
        )
        turnData.addGameData(
            GameData(
                ".....#...#...#....",
                6,
                6,
                12,
                1,
                0,
                2,
                -1
            )
        )
        turnData.addGameData(
            GameData(
                "GAME_OVER",
                30,
                1,
                0,
                2,
                -1,
                2,
                -1
            )
        )
        turnData.addGameData(
            GameData(
                ".....#...#...#....",
                25,
                1,
                0,
                2,
                -1,
                2,
                -1
            )
        )

        // act
        val gameToPlay = turnData.getGameWithBestPosition(0)

        // assert
        assertThat(gameToPlay).isEqualTo(2)
    }

    @Test
    fun should_return_game_with_best_medal_position() {
        // arrange
        val turnData = TurnData(
            ScoreInfo("0 1 0 0 1 0 0 1 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
            ScoreInfo("0 0 0 0 0 0 0 0 0 0 0 0 0"),
        )
        turnData.addGameData(
            GameData(
                ".....#...#...#....",
                8,
                6,
                5,
                1,
                0,
                2,
                -1
            )
        )
        turnData.addGameData(
            GameData(
                "GAME_OVER",
                30,
                1,
                0,
                2,
                -1,
                2,
                -1
            )
        )
        turnData.addGameData(
            GameData(
                ".....#...#...#....",
                25,
                28,
                28,
                2,
                -1,
                2,
                -1
            )
        )

        // act
        val gameToPlay = turnData.getGameWithBestMedalPosition(0)

        // assert
        assertThat(gameToPlay).isEqualTo(0)

    }

    @Test
    fun should_return_medal_placement_of_player_1() {
        // arrange
        val gameData = GameData(
            ".....#...#...#....",
            8,
            6,
            4,
            1,
            0,
            2,
            -1
        )

        // act
        val medal = gameData.getPlayerMedalPlacement(0)

        // assert
        assertThat(medal).isEqualTo(0)
    }

    @Test
    fun should_return_medal_placement_of_player_2() {
        // arrange
        val gameData = GameData(
            ".....#...#...#....",
            8,
            6,
            4,
            1,
            0,
            2,
            -1
        )

        // act
        val medal = gameData.getPlayerMedalPlacement(1)

        // assert
        assertThat(medal).isEqualTo(1)
    }

    @Test
    fun should_return_medal_placement_of_player_3() {
        // arrange
        val gameData = GameData(
            ".....#...#...#....",
            8,
            6,
            10,
            1,
            0,
            2,
            -1
        )

        // act
        val medal = gameData.getPlayerMedalPlacement(2)

        // assert
        assertThat(medal).isEqualTo(0)
    }

}
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OlymbitsTest {

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
            ScoreInfo("100 1 2 3"),
            ScoreInfo("200 4 5 6"),
            ScoreInfo("300 7 8 9"),
        )

        // assert
        assertThat(turnData.scoreInfoPlayer1.finalScore).isEqualTo(100)
        assertThat(turnData.scoreInfoPlayer1.goldMedals).isEqualTo(1)
        assertThat(turnData.scoreInfoPlayer1.silverMedals).isEqualTo(2)
        assertThat(turnData.scoreInfoPlayer1.bronzeMedals).isEqualTo(3)
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
        // arrange

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

}
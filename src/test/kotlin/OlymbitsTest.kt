import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OlymbitsTest {

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
}
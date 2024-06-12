import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OlymbitsTest {

    @Test
    fun should_collect_game_data() {
        // arrange

        // act
        val gameData = GameData(2, 0)

        // assert
        assertThat(gameData.numberOfGames).isEqualTo(2)
        assertThat(gameData.playerIndex).isEqualTo(0)
    }

    @Test
    fun should_collect_turn_data() {
        // act
        val turnData = TurnData(
            ScoreInfo("100 1 2 3"),
            ScoreInfo("200 4 5 6"),
            ScoreInfo("300 7 8 9"),
            ".....#...#...#....",
            0,
            6,
            12,
            1,
            0,
            2,
            -1
        )

        // assert
        assertThat(turnData.scoreInfoPlayer1.finalScore).isEqualTo(100)
        assertThat(turnData.scoreInfoPlayer1.goldMedals).isEqualTo(1)
        assertThat(turnData.scoreInfoPlayer1.silverMedals).isEqualTo(2)
        assertThat(turnData.scoreInfoPlayer1.bronzeMedals).isEqualTo(3)
        assertThat(turnData.raceTrack).isEqualTo(".....#...#...#....")
        assertThat(turnData.positionPlayer1).isEqualTo(0)
        assertThat(turnData.positionPlayer2).isEqualTo(6)
        assertThat(turnData.positionPlayer3).isEqualTo(12)
        assertThat(turnData.stunTimerPlayer1).isEqualTo(1)
        assertThat(turnData.stunTimerPlayer2).isEqualTo(0)
        assertThat(turnData.stunTimerPlayer3).isEqualTo(2)

    }
}
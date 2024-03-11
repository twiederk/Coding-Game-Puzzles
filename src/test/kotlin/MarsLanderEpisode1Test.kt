import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarsLanderEpisode1Test {

    @Test
    fun should_increase_power_when_v_speed_is_higher_than_35() {
        // arrange
        val turnData = TurnData(0, 0, 0, -40, 0, 0, 3)

        // act
        val move = MarsLanderEpisode1().move(turnData)

        // assert
        assertThat(move).isEqualTo(Pair(0, 4))
    }

    @Test
    fun should_stay_in_power_range_0_4() {
        // arrange
        val turnData = TurnData(0, 0, 0, -40, 0, 0, 4)

        // act
        val move = MarsLanderEpisode1().move(turnData)

        // assert
        assertThat(move).isEqualTo(Pair(0, 4))
    }
}
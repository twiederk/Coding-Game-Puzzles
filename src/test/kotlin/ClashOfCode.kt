import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.pow

class ClashOfCode {

    @Test
    fun should_calculate_max_of_input_with_power_of_min_of_input() {
        // arrange
        val input = listOf(5, 2, 3)

        // act
        val result = input.max().toFloat().pow(input.min()).toInt()

        // assert
        assertThat(result).isEqualTo(25)
    }

}
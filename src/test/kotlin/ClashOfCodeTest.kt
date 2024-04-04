import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.pow

class ClashOfCodeTest {

    @Test
    fun clash_of_code() {
        val n = 3

        // Write an answer using println()
        // To debug: System.err.println("Debug messages...");

        val a = (n - 2) * 180 / n
        val b = 180 - a
        println("$a $b")
        if (a % 2 == 0 && b % 2 == 0) println("even") else println("odd")
    }

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
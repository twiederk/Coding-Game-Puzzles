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

    @Test
    fun should_calculate_trapezoid() {
//        val input = Scanner(System.`in`)
        val a = 1
        val b = 2
        val h = 3

        println((a + b) * h / 2)
    }

    @Test
    fun should_convert_minutes_to_hours_and_minutes() {
        println("${100 / 60}h ${100 % 60}m")
        println("${30 / 60}h ${30 % 60}m")
    }

    @Test
    fun should_calculate_integration() {
        val N = 2.toDouble()
        val result = (N.pow(N + 1)) / (N + 1)
        println(String.format("%.2f", result))
    }

}
import org.junit.jupiter.api.Test
import kotlin.math.pow

class ClashOfCode {

    @Test
    fun exp() {
        // arrange
        val input = listOf(5, 2, 3)

        // act
        val result = input.max().toFloat().pow(input.min()).toInt()

        // assert
        println("$result")
    }

}
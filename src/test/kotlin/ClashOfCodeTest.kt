import org.junit.jupiter.api.Test

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
}
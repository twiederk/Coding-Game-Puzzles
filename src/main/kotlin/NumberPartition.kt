import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val n = input.nextInt()

    // Write an answer using println()
    // To debug: System.err.println("Debug messages...");

    println(NumberPartition().solve(n))
}


class NumberPartition {
    fun solve(n: Int): String {
        return n.toString()
    }
}
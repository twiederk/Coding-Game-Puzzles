import java.util.*
import kotlin.math.min

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

    fun solve(number: Int): String {
        val solution = recursion(number)
        return solution.joinToString(separator = "\n")
    }

    private fun recursion(number: Int): List<String> {
        val solutions = mutableListOf<String>()
        numberPartition(number, number, mutableListOf(), solutions)
        return solutions
    }

    private fun numberPartition(
        current: Int,
        number: Int,
        solution: MutableList<Int>,
        solutions: MutableList<String>
    ) {
        solution.add(current)
        if (current == 1) {
            solutions.add(solution.joinToString(separator = " ") { it.toString() })
            return
        }
        if (current != number) {
            solution
        }
        numberPartition(current - 1, number, solution, solutions)
    }

    private fun hardCoded(number: Int): List<String> = when (number) {
        1 -> listOf(number.toString())
        2 -> listOf("2", "1 1")
        3 -> listOf("3", "2 1", "1 1 1")
        4 -> listOf("4", "3 1", "2 2", "2 1 1", "1 1 1 1")
        5 -> listOf("5", "4 1", "3 2", "3 1 1", "2 2 1", "2 1 1 1", "1 1 1 1 1")
        6 -> listOf(
            "6", "5 1", "4 2", "4 1 1", "3 3", "3 2 1", "3 1 1 1", "2 2 2", "2 2 1 1", "2 1 1 1 1",
            "1 1 1 1 1 1"
        )

        7 -> listOf(
            "7", "6 1", "5 2", "5 1 1", "4 3", "4 2 1", "4 1 1 1", "3 3 1", "3 2 1 1", "3 1 1 1 1", "2 2 2 1",
            "2 2 1 1 1", "2 1 1 1 1 1", "1 1 1 1 1 1 1"
        )

        8 -> listOf(
            "8", "7 1", "6 2", "6 1 1", "5 3", "5 2 1", "5 1 1 1", "4 4", "4 3 1", "4 2 2", "4 2 1 1", "3 3 2",
            "3 3 1 1", "3 2 2 1 1", "2 2 2 2", "2 2 2 1 1", "2 2 1 1 1 1", "2 1 1 1 1 1 1", "1 1 1 1 1 1 1 1"
        )

        9 -> listOf(
            "9", "8 1", "7 2", "7 1 1", "6 3", "6 2 1", "6 1 1 1", "5 4", "5 3 1", "5 2 2", "5 2 1 1",
            "5 1 1 1 1", "4 4 1", "4 3 1 1", "4 2 2 1", "4 2 1 1 1", "4 1 1 1 1 1", "3 3 3", "3 3 2 1", "3 3 1 1 1",
            "3 2 1 1 1 1", "3 1 1 1 1 1 1", "2 1 1 1 1 1 1 1", "1 1 1 1 1 1 1 1 1"
        )

        else -> emptyList()
    }

    data class Work(
        val partition: List<Int>
    ) {
        fun next(number: Int): Work {
            val index = indexOfFirstPartitionLargerThanOne()
            val newPartition = mutableListOf<Int>()
            if (index > 0) {
                newPartition.addAll(partition.subList(0, index))
            } else {
                newPartition.add(partition[0] - 1)
            }
            while (newPartition.sum() != number) {
                newPartition.add(missingPartition(newPartition, number, index))
            }
            return Work(newPartition)
        }

        private fun missingPartition(newPartition: List<Int>, number: Int, index: Int): Int {
            return number - min(newPartition.sum(), partition[index] - 1)
        }

        fun indexOfFirstPartitionLargerThanOne(): Int {
            return partition.indexOfLast { it > 1 }
        }
    }

}
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

    fun solve(number: Int): String {
        val solutions = mutableListOf<Work>()
        var work = Work(listOf(number))
        solutions.add(work)
        while (work.partition.first() != 1) {
            work = work.next(number)
            solutions.add(work)
        }
        return solutions.joinToString("\n") { it.toString() }
    }

    data class Work(
        val partition: List<Int>
    ) {
        fun next(number: Int): Work {
            val index = indexOfFirstPartitionLargerThanOne()
            val newPartition = copyPartBeforeFirstPartitionLargerThanOne(index)
            while (newPartition.sum() != number) {
                newPartition.add(missingPartition(newPartition, number, partition[index] - 1))
            }
            return Work(newPartition)
        }

        private fun copyPartBeforeFirstPartitionLargerThanOne(index: Int): MutableList<Int> {
            val newPartition = mutableListOf<Int>()
            if (index > 0) {
                newPartition.addAll(partition.subList(0, index))
            } else {
                newPartition.add(partition[0] - 1)
            }
            return newPartition
        }

        private fun missingPartition(newPartition: List<Int>, number: Int, max: Int): Int {
            var missingPartition = max
            while (newPartition.sum() + missingPartition > number) {
                missingPartition--
            }
            return missingPartition
        }

        fun indexOfFirstPartitionLargerThanOne(): Int {
            return partition.indexOfLast { it > 1 }
        }

        override fun toString(): String {
            return partition.joinToString(" ")
        }
    }

}
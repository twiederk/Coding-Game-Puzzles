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

    println(NumberPartition().output(n))
}


class NumberPartition {
    fun solve(number: Int): List<String> {
        when (number) {
            1 -> return listOf(number.toString())
            2 -> return listOf("2", "1 1")
            3 -> return listOf("3", "2 1", "1 1 1")
            4 -> return listOf("4", "3 1", "2 2", "2 1 1", "1 1 1 1")
            5 -> return listOf("5", "4 1", "3 2", "3 1 1", "2 2 1", "2 1 1 1", "1 1 1 1 1")
            6 -> return listOf("6", "5 1", "4 2", "4 1 1", "3 3", "3 2 1", "3 1 1 1", "2 2 2", "2 2 1 1", "2 1 1 1 1", "1 1 1 1 1 1")
            7 -> return listOf("7", "6 1", "5 2", "5 1 1", "4 3", "4 2 1", "4 1 1 1", "3 3 1", "3 2 1 1", "3 1 1 1 1", "2 2 2 1", "2 2 1 1 1", "2 1 1 1 1 1", "1 1 1 1 1 1 1")
            8 -> return listOf("8", "7 1", "6 2", "6 1 1", "5 3", "5 2 1", "5 1 1 1", "4 4", "4 3 1", "4 2 2", "4 2 1 1", "3 3 2", "3 3 1 1", "3 2 2 1 1", "2 2 2 2", "2 2 2 1 1", "2 2 1 1 1 1", "2 1 1 1 1 1 1", "1 1 1 1 1 1 1 1")
            9 -> return listOf("9", "8 1", "7 2", "7 1 1", "6 3", "6 2 1", "6 1 1 1", "5 4", "5 3 1", "5 2 2", "5 2 1 1", "5 1 1 1 1", "4 4 1", "4 3 1 1", "4 2 2 1", "4 2 1 1 1", "4 1 1 1 1 1", "3 3 3", "3 3 2 1", "3 3 1 1 1", "3 2 1 1 1 1", "3 1 1 1 1 1 1", "2 1 1 1 1 1 1 1", "1 1 1 1 1 1 1 1 1")
            else -> return emptyList()
        }
    }

    fun dfs(number: Int): List<Term> {
        val terms = mutableListOf<Term>()
        val stack = Stack<Term>()
        stack.push(Term(number, number, 0))

        while (stack.isNotEmpty()) {
            val current = stack.pop()
            terms.add(current)
        }
        return terms
    }

    fun output(number: Int): String {
        val solution = solve(number)
        return solution.joinToString(separator = "\n")
    }


    data class Term(
        val number: Int,
        val summandA: Int = number,
        val summandB: Int = 0
    ) {
        fun subTerms(): List<Term> {
            val terms = mutableListOf<Term>()
            for (i in 1..number / 2) {
                terms.add(Term(number, i, number - i))
            }
            return terms
        }
    }
}
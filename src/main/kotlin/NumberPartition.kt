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
    fun solve(number: Int): List<String> {
        val solutions = dfs(number)
        return solutions.map { it.number.toString() }
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
import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    val persons = mutableListOf<OrderOfSuccession.Person>()
    for (i in 0 until n) {
        val name = input.next()
        val parent = input.next()
        val birth = input.nextInt()
        val death = input.next()
        val religion = input.next()
        val gender = input.next()
        persons.add(OrderOfSuccession.Person(name, parent, birth, death, religion, gender))
    }

    println(OrderOfSuccession().solve(persons))
}

class OrderOfSuccession {

    fun solve(persons: List<Person>): String {
        return persons.joinToString("\n") { it.name }
    }

    data class Person(
        val name: String,
        val parent: String,
        val birth: Int,
        val death: String,
        val religion: String,
        val gender: String
    ) {
        companion object {
            fun create(input: String): Person {
                val parts = input.split(" ")
                val name = parts[0]
                val parent = parts[1]
                val birth = parts[2].toInt()
                val death = parts[3]
                val religion = parts[4]
                val gender = parts[5]
                return Person(name, parent, birth, death, religion, gender)
            }
        }
    }


}
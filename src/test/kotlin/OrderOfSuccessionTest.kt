import OrderOfSuccession.Person
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderOfSuccessionTest {

    // Name Parent Year of birth Year of death Religion Gender
    private val test1 = listOf(
        Person("Elizabeth", "-", 1926, "-", "Anglican", "F"),
        Person("Charles", "Elizabeth", 1948, "-", "Anglican", "M"),
        Person("William", "Charles", 1982, "-", "Anglican", "M"),
        Person("George", "William", 2013, "-", "Anglican", "M"),
        Person("Charlotte", "William", 2015, "-", "Anglican", "F"),
        Person("Henry", "Charles", 1984, "-", "Anglican", "M"),
    )

    @Test
    fun should_parse_input_and_create_person() {
        // act
        val person = Person.create("Elizabeth - 1926 - Anglican F")

        // assert
        assertThat(person).isEqualTo(
            Person("Elizabeth", "-", 1926, "-", "Anglican", "F")
        )

    }

    @Test
    fun should_solve_test1() {
        // act
        val output = OrderOfSuccession().solve(test1)

        // assert
        assertThat(output).isEqualTo(
            """
            Elizabeth
            Charles
            William
            George
            Charlotte
            Henry
            """.trimIndent()
        )
    }

}


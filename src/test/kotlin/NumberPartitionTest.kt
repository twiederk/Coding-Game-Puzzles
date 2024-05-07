import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NumberPartitionTest {

    @Test
    fun should_solve_number_2() {

        // act
        val result = NumberPartition().hardCoded(2)

        // assert
        assertThat(result).containsExactly(
            "2",
            "1 1"
        )
    }

    @Test
    fun should_solve_number_3() {

        // act
        val result = NumberPartition().hardCoded(3)

        // assert
        assertThat(result).containsExactly(
            "3",
            "2 1",
            "1 1 1",
        )
    }

    @Test
    fun should_solve_number_4() {

        // act
        val result = NumberPartition().hardCoded(4)

        // assert
        assertThat(result).containsExactly(
            "4",
            "3 1",
            "2 2",
            "2 1 1",
            "1 1 1 1",
        )
    }

    @Test
    fun should_solve_number_5() {

        // act
        val result = NumberPartition().hardCoded(5)

        // assert
        assertThat(result).containsExactly(
            "5",
            "4 1",
            "3 2",
            "3 1 1",
            "2 2 1",
            "2 1 1 1",
            "1 1 1 1 1",
        )
    }

    @Test
    fun should_solve_number_6() {

        // act
        val result = NumberPartition().hardCoded(6)

        // assert
        assertThat(result).containsExactly(
            "6",
            "5 1",
            "4 2",
            "4 1 1",
            "3 3",
            "3 2 1",
            "3 1 1 1",
            "2 2 2",
            "2 2 1 1",
            "2 1 1 1 1",
            "1 1 1 1 1 1",
        )
    }

    @Test
    fun should_solve_number_7() {

        // act
        val result = NumberPartition().hardCoded(7)

        // assert
        assertThat(result).containsExactly(
            "7",
            "6 1",
            "5 2",
            "5 1 1",
            "4 3",
            "4 2 1",
            "4 1 1 1",
            "3 3 1",
            "3 2 1 1",
            "3 1 1 1 1",
            "2 2 2 1",
            "2 2 1 1 1",
            "2 1 1 1 1 1",
            "1 1 1 1 1 1 1",
        )
    }

    @Test
    fun should_solve_number_8() {

        // act
        val result = NumberPartition().hardCoded(8)

        // assert
        assertThat(result).containsExactly(
            "8",
            "7 1",
            "6 2",
            "6 1 1",
            "5 3",
            "5 2 1",
            "5 1 1 1",
            "4 4",
            "4 3 1",
            "4 2 2",
            "4 2 1 1",
            "3 3 2",
            "3 3 1 1",
            "3 2 2 1 1",
            "2 2 2 2",
            "2 2 2 1 1",
            "2 2 1 1 1 1",
            "2 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1",
        )
    }

    @Test
    fun should_solve_number_9() {

        // act
        val result = NumberPartition().hardCoded(9)

        // assert
        assertThat(result).containsExactly(
            "9",
            "8 1",
            "7 2",
            "7 1 1",
            "6 3",
            "6 2 1",
            "6 1 1 1",
            "5 4",
            "5 3 1",
            "5 2 2",
            "5 2 1 1",
            "5 1 1 1 1",
            "4 4 1",
            "4 3 1 1",
            "4 2 2 1",
            "4 2 1 1 1",
            "4 1 1 1 1 1",
            "3 3 3",
            "3 3 2 1",
            "3 3 1 1 1",
            "3 2 1 1 1 1",
            "3 1 1 1 1 1 1",
            "2 1 1 1 1 1 1 1",
            "1 1 1 1 1 1 1 1 1",
        )

    }

    @Test
    fun should_display_result() {
        // arrange

        // act
        val output = NumberPartition().solve(4)

        // assert
        assertThat(output).isEqualTo(
            """
            4
            3 1
            2 2
            2 1 1
            1 1 1 1
            """.trimIndent()
        )

    }
}
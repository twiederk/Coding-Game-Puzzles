import NumberPartition.Term
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class NumberPartitionTest {

    @Test
    fun should_solve_number_2() {

        // act
        val result = NumberPartition().solve(2)

        // assert
        assertThat(result).containsExactly(
            "2",
            "1 1"
        )
    }

    @Test
    fun should_solve_number_3() {

        // act
        val result = NumberPartition().solve(3)

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
        val result = NumberPartition().solve(4)

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
        val result = NumberPartition().solve(5)

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
        val result = NumberPartition().solve(6)

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
        val result = NumberPartition().solve(7)

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
        val result = NumberPartition().solve(8)

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
        val result = NumberPartition().solve(9)

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
    fun should_find_sums_when_number_2_is_given() {

        // act
        val subTerms = Term(2).subTerms()

        // assert
        assertThat(subTerms).containsExactly(
            Term(2, 1, 1)
        )
    }

    @Test
    fun should_find_sums_when_number_3_is_given() {

        // act
        val subTerms = Term(3).subTerms()

        // assert
        assertThat(subTerms).containsExactly(
            Term(3, 1, 2)
        )
    }

    @Test
    fun should_find_sums_when_number_4_is_given() {

        // act
        val subTerms = Term(4).subTerms()

        // assert
        assertThat(subTerms).containsExactly(
            Term(4, 1, 3),
            Term(4, 2, 2),
        )
    }

    @Test
    fun should_find_sums_when_number_5_is_given() {

        // act
        val subTerms = Term(5).subTerms()

        // assert
        assertThat(subTerms).containsExactly(
            Term(5, 1, 4),
            Term(5, 2, 3),
        )
    }

    @Test
    fun should_find_sums_when_number_6_is_given() {

        // act
        val subTerms = Term(6).subTerms()

        // assert
        assertThat(subTerms).containsExactly(
            Term(6, 1, 5),
            Term(6, 2, 4),
            Term(6, 3, 3),
        )
    }

    @Test
    @Disabled
    fun should_find_all_solutions_when_number_2_is_given() {

        // act
        val solutions = NumberPartition().dfs(2)

        // assert
        assertThat(solutions).containsExactly(
            Term(2),
            Term(2, 1, 1)
        )
    }

    @Test
    fun should_display_result() {
        // arrange

        // act
        val output = NumberPartition().output(4)

        // assert
        assertThat(output).isEqualTo("""
            4
            3 1
            2 2
            2 1 1
            1 1 1 1""".trimIndent())

    }
}
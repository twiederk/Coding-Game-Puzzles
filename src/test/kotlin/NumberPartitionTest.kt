import NumberPartition.Term
import org.assertj.core.api.Assertions.assertThat
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
    fun should_find_all_solutions_when_number_2_is_given() {

        // act
        val solutions = NumberPartition().dfs(2)

        // assert
        assertThat(solutions).containsExactly(
            Term(2),
            Term(2, 1, 1)
        )

    }
}
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
        val partitions = NumberPartition.Solution(2).partitions()

        // assert
        assertThat(partitions).containsExactly(
            listOf(1, 1)
        )
    }

    @Test
    fun should_find_sums_when_number_3_is_given() {

        // act
        val partitions = NumberPartition.Solution(3).partitions()

        // assert
        assertThat(partitions).containsExactly(
            listOf(1, 2)
        )
    }

    @Test
    fun should_find_sums_when_number_4_is_given() {

        // act
        val partitions = NumberPartition.Solution(4).partitions()

        // assert
        assertThat(partitions).containsExactly(
            listOf(1, 3),
            listOf(2, 2),
        )
    }

    @Test
    fun should_find_sums_when_number_5_is_given() {

        // act
        val partitions = NumberPartition.Solution(5).partitions()

        // assert
        assertThat(partitions).containsExactly(
            listOf(1, 4),
            listOf(2, 3),
        )
    }

    @Test
    fun should_find_sums_when_number_6_is_given() {

        // act
        val partitions = NumberPartition.Solution(6).partitions()

        // assert
        assertThat(partitions).containsExactly(
            listOf(1, 5),
            listOf(2, 4),
            listOf(3, 3),
        )
    }
}
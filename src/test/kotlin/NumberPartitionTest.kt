import NumberPartition.Work
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NumberPartitionTest {

    @Test
    fun should_solve_number_1() {

        // act
        val result = NumberPartition().solve(1)

        // assert
        assertThat(result).isEqualTo(
            """
            1
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_number_2() {

        // act
        val result = NumberPartition().solve(2)

        // assert
        assertThat(result).isEqualTo(
            """
            2
            1 1
            """.trimIndent()
        )

    }

    @Test
    fun should_solve_number_3() {

        // act
        val result = NumberPartition().solve(3)

        // assert
        assertThat(result).isEqualTo(
            """
            3
            2 1
            1 1 1
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_number_4() {

        // act
        val result = NumberPartition().solve(4)

        // assert
        assertThat(result).isEqualTo(
            """
            4
            3 1
            2 2
            2 1 1
            1 1 1 1
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_number_5() {

        // act
        val result = NumberPartition().solve(5)

        // assert
        assertThat(result).isEqualTo(
            """
            5
            4 1
            3 2
            3 1 1
            2 2 1
            2 1 1 1
            1 1 1 1 1
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_number_6() {

        // act
        val result = NumberPartition().solve(6)

        // assert
        assertThat(result).isEqualTo(
            """
            6
            5 1
            4 2
            4 1 1
            3 3
            3 2 1
            3 1 1 1
            2 2 2
            2 2 1 1
            2 1 1 1 1
            1 1 1 1 1 1
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_number_7() {

        // act
        val result = NumberPartition().solve(7)

        // assert
        assertThat(result).isEqualTo(
            """
            7
            6 1
            5 2
            5 1 1
            4 3
            4 2 1
            4 1 1 1
            3 3 1
            3 2 2
            3 2 1 1
            3 1 1 1 1
            2 2 2 1
            2 2 1 1 1
            2 1 1 1 1 1
            1 1 1 1 1 1 1
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_number_8() {

        // act
        val result = NumberPartition().solve(8)

        // assert
        assertThat(result).isEqualTo(
            """
            8
            7 1
            6 2
            6 1 1
            5 3
            5 2 1
            5 1 1 1
            4 4
            4 3 1
            4 2 2
            4 2 1 1
            4 1 1 1 1
            3 3 2
            3 3 1 1
            3 2 2 1
            3 2 1 1 1
            3 1 1 1 1 1
            2 2 2 2
            2 2 2 1 1
            2 2 1 1 1 1
            2 1 1 1 1 1 1
            1 1 1 1 1 1 1 1
            """.trimIndent()
        )
    }

    @Test
    fun should_solve_number_9() {

        // act
        val result = NumberPartition().solve(9)

        // assert
        assertThat(result).isEqualTo(
            """
            9
            8 1
            7 2
            7 1 1
            6 3
            6 2 1
            6 1 1 1
            5 4
            5 3 1
            5 2 2
            5 2 1 1
            5 1 1 1 1
            4 4 1
            4 3 2
            4 3 1 1
            4 2 2 1
            4 2 1 1 1
            4 1 1 1 1 1
            3 3 3
            3 3 2 1
            3 3 1 1 1
            3 2 2 2
            3 2 2 1 1
            3 2 1 1 1 1
            3 1 1 1 1 1 1
            2 2 2 2 1
            2 2 2 1 1 1
            2 2 1 1 1 1 1
            2 1 1 1 1 1 1 1
            1 1 1 1 1 1 1 1 1
            """.trimIndent()
        )
    }

    @Test
    fun should_find_next_work_when_given_6() {

        // act
        val next = Work(listOf(6)).next(6)

        // assert
        assertThat(next).isEqualTo(
            Work(listOf(5, 1))
        )
    }

    @Test
    fun should_find_next_work_when_given_5_1() {

        // act
        val next = Work(listOf(5, 1)).next(6)

        // assert
        assertThat(next).isEqualTo(
            Work(listOf(4, 2))
        )
    }

    @Test
    fun should_find_next_work_when_given_4_2() {

        // act
        val next = Work(listOf(4, 2)).next(6)

        // assert
        assertThat(next).isEqualTo(Work(listOf(4, 1, 1)))
    }

    @Test
    fun should_find_next_work_when_given_4_1_1() {

        // act
        val next = Work(listOf(4, 1, 1)).next(6)

        // assert
        assertThat(next).isEqualTo(Work(listOf(3, 3)))
    }

    @Test
    fun should_find_next_work_when_given_3_3() {

        // act
        val next = Work(listOf(3, 3)).next(6)

        // assert
        assertThat(next).isEqualTo(Work(listOf(3, 2, 1)))
    }

    @Test
    fun should_return_index_of_first_partition_which_is_larger_than_one_when_given_5_1() {

        // act
        val index = Work(listOf(5, 1)).indexOfFirstPartitionLargerThanOne()

        // assert
        assertThat(index).isEqualTo(0)
    }

    @Test
    fun should_return_string_when_partition_is_given() {

        // act
        val output = Work(listOf(5, 1)).toString()

        // assert
        assertThat(output).isEqualTo("5 1")
    }
}
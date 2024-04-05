import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MovesInMazeTest {

    private val test1 = listOf(
        "##########",
        "#S.......#",
        "##.#####.#",
        "##.#.....#",
        "##########"
    )

    @Test
    fun should_solve_test_1() {

        // act
        val result = MovesInMaze().solve(test1)

        // assert
        Assertions.assertThat(result).isEqualTo(
            """
            ##########
            #01234567#
            ##2#####8#
            ##3#DCBA9#
            ##########
        """.trimIndent()
        )

    }
}
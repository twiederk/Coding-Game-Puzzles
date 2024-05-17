import Alphabet.Point2D
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AlphabetTest {

    val test1 = listOf(
        "vkbjbzmbgb",
        "abcccpzouv",
        "fedopwlmcl",
        "glmnqrszyw",
        "hkrhiutymj",
        "ijqcmvwxoc",
        "pcvlpqzphl",
        "hsgvoklcxy",
        "urdjusmbmz",
        "rchbcausnp",

        )

    @Test
    fun should_solve_test1() {

        // act
        val result = Alphabet().solve(test1)

        // assert
        assertThat(result).isEqualTo(
            """
            ----------
            abc-------
            fedop-----
            glmnqrsz--
            hk---uty--
            ij---vwx--
            ----------
            ----------
            ----------
            ----------
        """.trimIndent()
        )
    }

    @Test
    fun should_find_all_occurrences_of_letter_a_when_maze_is_given() {

        // act
        val startingPoints = Alphabet().startingPoints(test1)

        // assert
        assertThat(startingPoints).containsExactly(
            Point2D(0, 1),
            Point2D(5, 9),
        )
    }

    @Test
    fun should_find_b_after_a_when_test1_is_given() {

        // act
        val neighbor = Point2D(0, 1).neighbor(test1, 'b')

        // assert
        assertThat(neighbor).isEqualTo(Point2D(1, 1))
    }

    @Test
    fun should_find_whole_path_when_test1_with_correct_starting_point_is_given() {

        // act
        val path = Alphabet().bfs(test1, Point2D(0, 1))

        // assert
        assertThat(path).hasSize(26)
        path.forEach(::println)
    }

    @Test
    fun should_render_path_only_when_grid_and_path_is_given() {
        // arrange
        val path = listOf(
            Point2D(x = 0, y = 1),
            Point2D(x = 1, y = 1),
            Point2D(x = 2, y = 1),
            Point2D(x = 2, y = 2),
            Point2D(x = 1, y = 2),
            Point2D(x = 0, y = 2),
            Point2D(x = 0, y = 3),
            Point2D(x = 0, y = 4),
            Point2D(x = 0, y = 5),
            Point2D(x = 1, y = 5),
            Point2D(x = 1, y = 4),
            Point2D(x = 1, y = 3),
            Point2D(x = 2, y = 3),
            Point2D(x = 3, y = 3),
            Point2D(x = 3, y = 2),
            Point2D(x = 4, y = 2),
            Point2D(x = 4, y = 3),
            Point2D(x = 5, y = 3),
            Point2D(x = 6, y = 3),
            Point2D(x = 6, y = 4),
            Point2D(x = 5, y = 4),
            Point2D(x = 5, y = 5),
            Point2D(x = 6, y = 5),
            Point2D(x = 7, y = 5),
            Point2D(x = 7, y = 4),
            Point2D(x = 7, y = 3),
        )

        // act
        val result = Alphabet().renderPath(test1, path)

        // assert
        assertThat(result).isEqualTo(
            """
            ----------
            abc-------
            fedop-----
            glmnqrsz--
            hk---uty--
            ij---vwx--
            ----------
            ----------
            ----------
            ----------
        """.trimIndent()
        )
    }

}
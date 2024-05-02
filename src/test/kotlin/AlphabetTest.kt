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
    fun should_find_all_occurrences_of_letter_when_maze_is_given() {

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
        // arrange

        // act
        val neighbor = Point2D(0, 1).neighbor(test1, 'a')

        // assert
        assertThat(neighbor).isEqualTo(Point2D(1, 1))

    }
}
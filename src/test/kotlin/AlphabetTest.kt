import org.assertj.core.api.Assertions
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
        Assertions.assertThat(result).isEqualTo(
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
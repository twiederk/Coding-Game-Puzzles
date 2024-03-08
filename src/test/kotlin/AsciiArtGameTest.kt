import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AsciiArtGameTest {

    val row = listOf(
        " #  ##   ## ##  ### ###  ## # # ###  ## # # #   # # ###  #  ##   #  ##   ## ### # # # # # # # # # # ###",
        "# # # # #   # # #   #   #   # #  #    # # # #   ### # # # # # # # # # # #    #  # # # # # # # # # #   #",
        "### ##  #   # # ##  ##  # # ###  #    # ##  #   ### # # # # ##  # # ##   #   #  # # # # ###  #   #   # ",
        "# # # # #   # # #   #   # # # #  #  # # # # #   # # # # # # #    ## # #   #  #  # # # # ### # #  #  #  ",
        "# # ##   ## ##  ### #    ## # # ###  #  # # ### # # # #  #  #     # # # ##   #  ###  #  # # # #  #  ###",
    )

    @Test
    fun should_display_first_line_of_letter_A() {
        // arrange

        // act
        val output = AsciiArtGame(row).readLetter("A")

        // assert
        assertThat(output).hasSize(1)
        assertThat(output[0]).isEqualTo(" #  ")
    }

    @Test
    fun should_return_index_of_letter_A() {

        // act
        val indexOfLetter = AsciiArtGame(row).getIndexOfLetter("A")

        // assert
        assertThat(indexOfLetter).isEqualTo(0)
    }

    @Test
    fun should_return_index_of_letter_B() {

        // act
        val indexOfLetter = AsciiArtGame(row).getIndexOfLetter("B")

        // assert
        assertThat(indexOfLetter).isEqualTo(1)
    }

}
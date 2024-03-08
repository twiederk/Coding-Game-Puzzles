class AsciiArtGame(
    private val row: List<String>
) {

    fun readLetter(letter: String): List<String> {
        return listOf()
    }

    fun getIndexOfLetter(letter: String): Int {
        return letter.toCharArray()[0].code - 'A'.code
    }

}

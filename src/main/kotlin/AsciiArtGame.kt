class AsciiArtGame(
    private val ROW: List<String>
) {

    fun readLetter(letter: String): List<String> {
        return listOf()
    }

    fun getIndexOfLetter(letter: String): Int {
        return letter.toCharArray()[0].code - 'A'.code
    }

}

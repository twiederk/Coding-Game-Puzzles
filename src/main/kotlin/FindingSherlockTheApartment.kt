class FindingSherlockTheApartment {

    fun compute(): List<String> {

        val jump = mapOf(
            'A' to Pair(-1, -2),
            'B' to Pair(+1, -2),
            'C' to Pair(+2, -1),
            'D' to Pair(+2, +1),
            'E' to Pair(+1, +2),
            'F' to Pair(-1, +2),
            'G' to Pair(-2, +1),
            'H' to Pair(-2, -1),
        )

        //                  01234567
        val chessLetters = "ABCDEFGH"

        //                  01234567
        val chessNumbers = "87654321"

        val moves = "AGDBBEFAAFHFDBFDDHHHBDFFBCCHHF"

        val positions = mutableListOf<String>()
        var curr = Pair(5, 4).copy()
        for (char in moves) {
            val move = jump[char]!!
            curr = Pair(curr.first + move.first, curr.second + move.second)
            val firstLetter = chessLetters[curr.first]
            val secondNumber = chessNumbers[curr.second]
            positions.add("$firstLetter$secondNumber")
        }
        return positions
    }

}
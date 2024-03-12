import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val numberOfCols = input.nextInt()
    val numberOfRows = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }
    for (i in 0 until numberOfRows) {
        val line = input.nextLine()
    }

    // Write an answer using println()
    // To debug: System.err.println("Debug messages...");

    println("answer")
}

class FloodFillExample {
    fun fill(map: List<String>, maxSteps: Int): List<String> {
        // find all towers on the map (starting points)
        // flood fill from each tower
        // return list of flood
        return map
    }

    data class DefenceMap(
        val map: List<String>
    ) {
        val width = map.first().length
        val height = map.size

        fun towers(): List<Tower> {
            val towers = mutableListOf<Tower>()
            for (y in 0 until height) {
                for (x in 0 until width) {
                    if (map[y][x] in 'A'..'Z') {
                        towers.add(Tower(map[y][x], x, y))
                    }
                }
            }
            return towers
        }
    }

    data class Tower(
        val id: Char,
        val x: Int,
        val y: Int
    )


}
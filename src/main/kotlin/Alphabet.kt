import java.util.*

fun main() {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    val maze = mutableListOf<String>()
    for (i in 0 until n) {
        maze.add(input.next())
    }

    val alphabet = Alphabet()
    println(alphabet.solve(maze))
}

class Alphabet {
    fun solve(maze: List<String>): String {
        return maze.joinToString("\n")
    }

    fun startingPoints(test1: List<String>): List<Point2D> {
        val startingPoints = mutableListOf<Point2D>()
        for (y in test1.indices) {
            for (x in test1[y].indices) {
                if (test1[y][x] == 'a') {
                    startingPoints.add(Point2D(x, y))
                }
            }
        }
        return startingPoints
    }

    data class Point2D(val x: Int, val y: Int)
}
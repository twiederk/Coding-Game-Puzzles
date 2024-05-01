import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val xs = input.nextInt()
    val ys = input.nextInt()
    val xr = input.nextInt()
    val yr = input.nextInt()
    input.nextInt()
    val h = input.nextInt()
    val labyrinth = mutableListOf<String>()
    for (i in 0 until h) {
        labyrinth.add(input.next())
    }

    val paperLabyrinth = PaperLabyrinth(
        start = PaperLabyrinth.Point2D(xs, ys),
        rabbit = PaperLabyrinth.Point2D(xr, yr),
        labyrinth = labyrinth
    )

    val (stepsToRabbit, stepsToExit) = paperLabyrinth.solve()
    println("$stepsToRabbit $stepsToExit")
}


class PaperLabyrinth(
    val start: Point2D,
    val rabbit: Point2D,
    val labyrinth: MutableList<String>
) {
    fun solve(): Pair<Int, Int> {
        System.err.println("start: $start")
        System.err.println("rabbit: $rabbit")
        System.err.println("labyrinth: $labyrinth")



        return Pair(0, 0)
    }

    data class Point2D(
        val x: Int,
        val y: Int
    ) {
        private val neighbors = mapOf(
            '0' to listOf(LEFT, RIGHT, DOWN, TOP),
            '1' to listOf(LEFT, RIGHT, TOP),
            '2' to listOf(RIGHT, DOWN, TOP),
            '3' to listOf(RIGHT, TOP),
            '4' to listOf(LEFT, RIGHT, DOWN),
            '5' to listOf(LEFT, RIGHT, DOWN, TOP),
            '6' to listOf(LEFT, RIGHT, DOWN, TOP),
            '7' to listOf(LEFT, RIGHT, DOWN, TOP),
            '8' to listOf(LEFT, RIGHT, DOWN, TOP),
            '9' to listOf(LEFT, RIGHT, DOWN, TOP),
            'a' to listOf(LEFT, RIGHT, DOWN, TOP),
            'b' to listOf(LEFT, RIGHT, DOWN, TOP),
            'c' to listOf(LEFT, RIGHT, DOWN, TOP),
            'd' to listOf(LEFT, RIGHT, DOWN, TOP),
            'e' to listOf(LEFT, RIGHT, DOWN, TOP),
            'f' to listOf(LEFT, RIGHT, DOWN, TOP),
        )

        fun next(cell: Char): List<Point2D> {
            return neighbors[cell]!!.map { this + it }
        }

        operator fun minus(other: Point2D): Point2D =
            Point2D(x - other.x, y - other.y)

        operator fun plus(other: Point2D): Point2D =
            Point2D(x + other.x, y + other.y)

        companion object {
            val TOP = Point2D(0, -1)
            val DOWN = Point2D(0, 1)
            val LEFT = Point2D(-1, 0)
            val RIGHT = Point2D(1, 0)
        }
    }

}
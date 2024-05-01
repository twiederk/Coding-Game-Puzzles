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
    val labyrinth: List<String>
) {
    fun solve(): Pair<Int, Int> {
        System.err.println("start: $start")
        System.err.println("rabbit: $rabbit")
        System.err.println("labyrinth: $labyrinth")

        val stepsToRabbit = bfs(start, rabbit)
        val stepsToExit = bfs(rabbit, start)

        return Pair(stepsToRabbit, stepsToExit)
    }

    fun bfs(start: Point2D, end: Point2D): Int {
        val queue = LinkedList<Work>()
        queue.add(Work(start, 0))
        val visited = mutableSetOf<Point2D>()

        while (queue.isNotEmpty()) {
            val work = queue.poll()
            if (work.point == end) {
                return work.steps
            }
            visited.add(work.point)
            val neighbors = work.point.neighbors(wall(work.point))
            for (neighbor in neighbors) {
                if (neighbor !in visited) {
                    queue.add(Work(neighbor, work.steps + 1))
                }
            }
        }
        throw IllegalStateException("End not found")
    }

    fun wall(point: Point2D): Char {
        return labyrinth[point.y][point.x]
    }

    data class Work(
        val point: Point2D,
        val steps: Int
    )

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
            '5' to listOf(LEFT, RIGHT),
            '6' to listOf(RIGHT, DOWN),
            '7' to listOf(RIGHT),
            '8' to listOf(LEFT, DOWN, TOP),
            '9' to listOf(LEFT, TOP),
            'a' to listOf(DOWN, TOP),
            'b' to listOf(TOP),
            'c' to listOf(LEFT, DOWN),
            'd' to listOf(LEFT),
            'e' to listOf(DOWN),
            'f' to emptyList(),
        )

        fun neighbors(wall: Char): List<Point2D> {
            return neighbors[wall]!!.map { this + it }
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
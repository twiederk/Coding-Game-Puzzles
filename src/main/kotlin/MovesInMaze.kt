import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    input.nextInt()
    val h = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }
    val maze = mutableListOf<String>()
    for (i in 0 until h) {
        maze.add(input.nextLine())
    }
    println(MovesInMaze().solve(MovesInMaze.Maze(maze)))
}

class MovesInMaze {

    fun solve(maze: Maze): String {
        val start = maze.start()
        val flood = floodfill(maze, start)
        return render(maze, flood)
    }


    fun floodfill(maze: Maze, start: Point2D): Set<Flood> {
        val queue = LinkedList<Flood>()
        val seen = mutableSetOf<Flood>()

        queue.add(Flood(start, 0))
        while (!queue.isEmpty()) {

            val curr = queue.poll()
            if (curr.coords in seen.map { it.coords }) {
                continue
            }

            seen.add(curr)
            maze.neighbors(curr.coords).forEach { neighbor ->
                queue.add(Flood(neighbor, curr.steps + 1))
            }
        }

        return seen
    }

    fun render(maze: Maze, flood: Set<Flood>): String {
        val floodedMaze = Array(maze.height) { CharArray(maze.width) { 'a' } }
        for (y in 0 until maze.height) {
            for (x in 0 until maze.width) {
                if (Point2D(x, y) in flood.map { it.coords }) {
                    floodedMaze[y][x] = stepSign(flood.first { it.coords == Point2D(x, y) }.steps)
                } else {
                    floodedMaze[y][x] = maze.get(x, y)
                }
            }
        }
        return floodedMaze.joinToString(separator = "\n") { it.joinToString(separator = "") }
    }

    fun stepSign(steps: Int): Char {
        return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"[steps]
    }

    data class Point2D(
        val x: Int,
        val y: Int
    )

    data class Flood(
        val coords: Point2D,
        val steps: Int
    )

    data class Maze(
        private val data: List<String>,
        val width: Int = data[0].length,
        val height: Int = data.size
    ) {

        fun start(): Point2D {
            for (y in data.indices) {
                for (x in data[y].indices) {
                    if (data[y][x] == 'S') {
                        return Point2D(x, y)
                    }
                }
            }
            throw IllegalStateException("No start found")
        }

        fun neighbors(point: Point2D): List<Point2D> {
            val neighbors = mutableListOf<Point2D>()
            if (data[point.y][point.x - 1] == '.') {
                neighbors.add(Point2D(point.x - 1, point.y))
            }
            if (data[point.y][point.x + 1] == '.') {
                neighbors.add(Point2D(point.x + 1, point.y))
            }
            if (data[point.y - 1][point.x] == '.') {
                neighbors.add(Point2D(point.x, point.y - 1))
            }
            if (data[point.y + 1][point.x] == '.') {
                neighbors.add(Point2D(point.x, point.y + 1))
            }
            return neighbors
        }

        fun get(x: Int, y: Int): Char = data[y][x]
    }

}
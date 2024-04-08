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
    println(MovesInMaze().solve(maze))
}

class MovesInMaze {

    fun solve(maze: List<String>): String {
        val start = start(maze)
        floodfill(maze, start)
        // create filled maze
        return maze.joinToString("\n")
    }

    fun start(maze: List<String>): Point2D {
        for (y in maze.indices) {
            for (x in maze[y].indices) {
                if (maze[y][x] == 'S') {
                    return Point2D(x, y)
                }
            }
        }
        throw IllegalStateException("No start found")
    }

    fun floodfill(maze: List<String>, start: Point2D): Set<Flood> {
        val queue = LinkedList<Flood>()
        val seen = mutableSetOf<Flood>()

        queue.add(Flood(start, 0))
        while (!queue.isEmpty()) {

            val curr = queue.poll()
            if (curr.coords in seen.map { it.coords }) {
                continue
            }

            seen.add(curr)
            curr.coords.neighbors(maze).forEach { neighbor ->
                queue.add(Flood(neighbor, curr.steps + 1))
            }
        }

        return seen
    }

    data class Point2D(
        val x: Int,
        val y: Int
    ) {
        fun neighbors(maze: List<String>): List<Point2D> {
            val neighbors = mutableListOf<Point2D>()
            if (maze[y][x - 1] == '.') {
                neighbors.add(Point2D(x - 1, y))
            }
            if (maze[y][x + 1] == '.') {
                neighbors.add(Point2D(x + 1, y))
            }
            if (maze[y - 1][x] == '.') {
                neighbors.add(Point2D(x, y - 1))
            }
            if (maze[y + 1][x] == '.') {
                neighbors.add(Point2D(x, y + 1))
            }
            return neighbors
        }
    }

    data class Flood(
        val coords: Point2D,
        val steps: Int
    )

}
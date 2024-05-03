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

    fun startingPoints(grid: List<String>): List<Point2D> {
        val startingPoints = mutableListOf<Point2D>()
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == 'a') {
                    startingPoints.add(Point2D(x, y))
                }
            }
        }
        return startingPoints
    }

    fun bfs(grid: List<String>, start: Point2D): List<Point2D> {
        val queue = LinkedList<Point2D>()
        queue.add(start)
        val visited = mutableSetOf<Point2D>()
        visited.add(start)
        var letter = 'a'
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            visited.add(current)
            val neighbor = current.neighbor(grid, letter + 1)
            if (neighbor != null) {
                queue.add(neighbor)
                visited.add(neighbor)
            }
            if (letter + 1 == 'z') {
                return visited.toList()
            }
            letter++
        }
        return emptyList()
    }

    fun renderPath(grid: List<String>, path: List<Point2D>): String {
        val output = StringBuilder()
        grid.forEachIndexed { y, row ->
            row.forEachIndexed { x, letter ->
                if (path.contains(Point2D(x, y))) {
                    output.append(letter)
                } else {
                    output.append("-")
                }
            }
            output.append("\n")
        }
        return output.toString().trimEnd()
    }


    data class Point2D(val x: Int, val y: Int) {
        fun neighbor(grid: List<String>, letter: Char): Point2D? {
            // top
            if (y - 1 >= 0 && grid[y - 1][x] == letter) {
                return Point2D(x, y - 1)
            }
            // bottom
            if (y + 1 < grid.size && grid[y + 1][x] == letter) {
                return Point2D(x, y + 1)
            }
            // left
            if (x - 1 >= 0 && grid[y][x - 1] == letter) {
                return Point2D(x - 1, y)
            }
            // right
            if (x + 1 < grid[y].length && grid[y][x + 1] == letter) {
                return Point2D(x + 1, y)
            }
            return null
        }

    }
}
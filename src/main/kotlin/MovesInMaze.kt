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
        // find start
        // flood fill
        // create filled maze
        return maze.joinToString("\n")
    }

}
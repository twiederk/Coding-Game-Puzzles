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
    val w = input.nextInt()
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
    )

}
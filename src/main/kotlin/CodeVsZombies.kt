// https://www.codingame.com/ide/puzzle/code-vs-zombies
import java.util.*
import kotlin.math.abs

/**
 * Save humans, destroy zombies!
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)

    val codeVsZombies = CodeVsZombies()

    // game loop
    while (true) {
        val x = input.nextInt()
        val y = input.nextInt()
        val position = Point2D(x, y)


        val humans = mutableListOf<Human>()
        val humanCount = input.nextInt()
        for (i in 0 until humanCount) {
            val humanId = input.nextInt()
            val humanX = input.nextInt()
            val humanY = input.nextInt()
            humans.add(Human(humanId, Point2D(humanX, humanY)))
        }

        val zombies = mutableListOf<Zombie>()
        val zombieCount = input.nextInt()
        for (i in 0 until zombieCount) {
            val zombieId = input.nextInt()
            val zombieX = input.nextInt()
            val zombieY = input.nextInt()
            val zombieXNext = input.nextInt()
            val zombieYNext = input.nextInt()
            zombies.add(Zombie(zombieId, Point2D(zombieX, zombieY), Point2D(zombieXNext, zombieYNext)))
        }

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");
        val move = codeVsZombies.move(position, humans, zombies)

        println("${move.x} ${move.y}") // Your destination coordinates
    }
}

class CodeVsZombies {
    fun move(position: Point2D, humans: List<Human>, zombies: List<Zombie>): Point2D {
        return Point2D(0, 0)
    }
}

data class Zombie(
    val zombieId: Int,
    val position: Point2D,
    val nextPosition: Point2D
)

data class Human(
    val humanId: Int,
    val point2D: Point2D
)

data class Point2D(
    val x: Int,
    val y: Int
) {
    operator fun minus(other: Point2D): Point2D =
        Point2D(x - other.x, y - other.y)

    operator fun plus(other: Point2D): Point2D =
        Point2D(x + other.x, y + other.y)

    fun manhattenDistance(other: Point2D): Int =
        abs(x - other.x) + abs(y - other.y)
}
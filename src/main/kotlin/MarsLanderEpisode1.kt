import java.util.*
import kotlin.math.min

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val surfaceN = input.nextInt() // the number of points used to draw the surface of Mars.
    for (i in 0 until surfaceN) {
        val landX = input.nextInt() // X coordinate of a surface point. (0 to 6999)
        val landY =
            input.nextInt() // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
    }

    val marsLanderEpisode1 = MarsLanderEpisode1()

    // game loop
    while (true) {
        val x = input.nextInt()
        val y = input.nextInt()
        val hSpeed = input.nextInt() // the horizontal speed (in m/s), can be negative.
        val vSpeed = input.nextInt() // the vertical speed (in m/s), can be negative.
        val fuel = input.nextInt() // the quantity of remaining fuel in liters.
        val rotate = input.nextInt() // the rotation angle in degrees (-90 to 90).
        val power = input.nextInt() // the thrust power (0 to 4).

        val turnData = TurnData(
            x = x,
            y = y,
            hSpeed = hSpeed,
            vSpeed = vSpeed,
            fuel = fuel,
            rotate = rotate,
            power = power
        )

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");
        val move = marsLanderEpisode1.move(turnData)

        // 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
        println("${move.first} ${move.second}")
    }

}

data class TurnData(
    val x: Int,
    val y: Int,
    val hSpeed: Int,
    val vSpeed: Int,
    val fuel: Int,
    val rotate: Int,
    val power: Int
)

class MarsLanderEpisode1 {

    fun move(turnData: TurnData): Pair<Int, Int> {
        var power = turnData.power
        if (turnData.vSpeed < -35) {
            power = min(turnData.power + 1, 4)
        }
        return Pair(0, power)
    }

}


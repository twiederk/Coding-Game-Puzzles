import java.util.*
import kotlin.math.sqrt

/**
 * Save the Planet.
 * Use less Fossil Fuel.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val N = input.nextInt() // the number of points used to draw the surface of Mars.
    val points = mutableListOf<MarsLanderEpisode2.Point2D>()
    for (i in 0 until N) {
        val landX = input.nextInt() // X coordinate of a surface point. (0 to 6999)
        val landY =
            input.nextInt() // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
        points.add(MarsLanderEpisode2.Point2D(landX, landY))
    }

    val marsLanderEpisode2 = MarsLanderEpisode2(MarsLanderEpisode2.Surface(points))

    // game loop
    while (true) {
        val x = input.nextInt()
        val y = input.nextInt()
        val hSpeed = input.nextInt() // the horizontal speed (in m/s), can be negative.
        val vSpeed = input.nextInt() // the vertical speed (in m/s), can be negative.
        val fuel = input.nextInt() // the quantity of remaining fuel in liters.
        val rotation = input.nextInt() // the rotation angle in degrees (-90 to 90).
        val power = input.nextInt() // the thrust power (0 to 4).

        val turnData = MarsLanderEpisode2.TurnData(
            x = x,
            y = y,
            hSpeed = hSpeed,
            vSpeed = vSpeed,
            fuel = fuel,
            rotate = rotation,
            power = power
        )

        val (angle, thrust) = marsLanderEpisode2.move(turnData)

        // R P. R is the desired rotation angle. P is the desired thrust power.
        println("$angle $thrust")
    }
}

class MarsLanderEpisode2(private val surface: Surface) {

    init {
        System.err.println("surface: $surface")
    }

    fun move(turnData: TurnData): Pair<Int, Int> {
        System.err.println("turnData: $turnData")
        if (turnData.x < surface.landingArea().first.x) {
            val distanceToLandingArea = surface.landingArea().first.x - turnData.x
            val thrust = getThrust(distanceToLandingArea)
            return Pair(-45, thrust)
        }
        if (turnData.x > surface.landingArea().second.x) {
            val distanceToLandingArea = turnData.x - surface.landingArea().second.x
            val thrust = getThrust(distanceToLandingArea)
            return Pair(45, thrust)
        }
        if (turnData.hSpeed > 20) {
            return Pair(45, 3)
        }
        if (turnData.hSpeed < -20) {
            return Pair(-45, 3)
        }
        return Pair(0, 3)
    }

    private fun getThrust(distanceToLandingArea: Int): Int {
        System.err.println("distanceToLandingArea: $distanceToLandingArea")
        return when (distanceToLandingArea) {
            in 0..250 -> 1
            in 250..500 -> 2
            in 500..750 -> 3
            else -> 4
        }
    }

    data class Surface(val points: List<Point2D>) {

        fun landingArea(): Pair<Point2D, Point2D> {
            val landingArea = points.zipWithNext().first { (p1, p2) -> p1.y == p2.y }
            return Pair(landingArea.first, landingArea.second)
        }
    }

    data class Point2D(val x: Int, val y: Int) {

        fun distanceTo(other: Point2D): Double {
            val dx = x - other.x
            val dy = y - other.y
            return sqrt((dx * dx + dy * dy).toDouble())
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
}


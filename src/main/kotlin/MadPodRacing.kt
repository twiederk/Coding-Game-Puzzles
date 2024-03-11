import java.util.*

/**
 * This code automatically collects game data in an infinite loop.
 * It uses the standard input to place data into the game variables such as x and y.
 * YOU DO NOT NEED TO MODIFY THE INITIALIZATION OF THE GAME VARIABLES.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)

    // game loop
    while (true) {
        val x = input.nextInt() // x position of your pod
        val y = input.nextInt() // y position of your pod
        val nextCheckpointX = input.nextInt() // x position of the next check point
        val nextCheckpointY = input.nextInt() // y position of the next check point

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");


        // Edit this line to output the target position
        // and thrust (0 <= thrust <= 100)
        // i.e.: "x y thrust"
        println("$nextCheckpointY $nextCheckpointY 50")


    }
}
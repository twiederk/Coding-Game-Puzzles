import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val playerIdx = input.nextInt()
    val nbGames = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }

    // game loop
    while (true) {
        for (i in 0 until 3) {
            val scoreInfo = input.nextLine()
        }
        for (i in 0 until nbGames) {
            val gpu = input.next()
            val reg0 = input.nextInt()
            val reg1 = input.nextInt()
            val reg2 = input.nextInt()
            val reg3 = input.nextInt()
            val reg4 = input.nextInt()
            val reg5 = input.nextInt()
            val reg6 = input.nextInt()
        }
        input.nextLine()

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");

        println("LEFT")
    }
}

data class GameData(
    val numberOfGames: Int,
    val playerIndex: Int
)

data class TurnData(
    val scoreInfoPlayer1: ScoreInfo,
    val scoreInfoPlayer2: ScoreInfo,
    val scoreInfoPlayer3: ScoreInfo,
    val raceTrack: String,
    val positionPlayer1: Int,
    val positionPlayer2: Int,
    val positionPlayer3: Int,
    val stunTimerPlayer1: Int,
    val stunTimerPlayer2: Int,
    val stunTimerPlayer3: Int,
    val reg6: Int,
)

class ScoreInfo(line: String) {
    val finalScore: Int = line.split(" ")[0].toInt()
    val goldMedals: Int = line.split(" ")[1].toInt()
    val silverMedals: Int = line.split(" ")[2].toInt()
    val bronzeMedals: Int = line.split(" ")[3].toInt()
}
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
    val raceData = RaceData(nbGames, playerIdx)
    val olymbits = Olymbits(raceData)

    // game loop
    while (true) {

        val scoreInfo1 = input.nextLine()
        val scoreInfo2 = input.nextLine()
        val scoreInfo3 = input.nextLine()
        val turnData = TurnData(
            ScoreInfo(scoreInfo1),
            ScoreInfo(scoreInfo2),
            ScoreInfo(scoreInfo3),
        )

        for (i in 0 until nbGames) {
            val gpu = input.next()
            val reg0 = input.nextInt()
            val reg1 = input.nextInt()
            val reg2 = input.nextInt()
            val reg3 = input.nextInt()
            val reg4 = input.nextInt()
            val reg5 = input.nextInt()
            val reg6 = input.nextInt()
            turnData.addGameData(
                GameData(
                    gpu,
                    reg0,
                    reg1,
                    reg2,
                    reg3,
                    reg4,
                    reg5,
                    reg6
                )
            )
        }
        input.nextLine()

        val action = olymbits.playTurn(turnData)
        println(action)
    }
}

data class RaceData(
    val numberOfGames: Int,
    val playerIndex: Int
)

data class TurnData(
    val scoreInfoPlayer1: ScoreInfo,
    val scoreInfoPlayer2: ScoreInfo,
    val scoreInfoPlayer3: ScoreInfo,
) {
    val games = mutableListOf<GameData>()

    fun addGameData(gameData: GameData) {
        games.add(gameData)
    }
}

data class GameData(
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

data class Olymbits(val raceData: RaceData) {
    fun playTurn(turnData: TurnData): String {
        return "RIGHT"
    }
}

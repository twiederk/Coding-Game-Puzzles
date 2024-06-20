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

    fun indexOfGameWithLeastMedals(playerIndex: Int): Int {
        val medals = when (playerIndex) {
            0 -> scoreInfoPlayer1.miniGames
            1 -> scoreInfoPlayer2.miniGames
            else -> scoreInfoPlayer3.miniGames
        }
        for (i in medals.indices) {
            if (medals[i].total() == medals.minOf { it.total() }) {
                return i
            }
        }
        return 0
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
) {
    fun countGameHurdles(): Int = raceTrack.count { it == '#' }

    fun getPlayerPosition(i: Int): Int {
        return when (i) {
            0 -> positionPlayer1
            1 -> positionPlayer2
            else -> positionPlayer3
        }
    }
}

class ScoreInfo(line: String) {
    val miniGames  = mutableListOf<Medals>()
    val finalScore: Int = line.substringBefore(" ").toInt()

    init {
        val medals = line.substringAfter(" ")
        medals.split(" ").chunked(3).forEach {
            miniGames.add(Medals(it[0].toInt(), it[1].toInt(), it[2].toInt()))
        }
    }
}

data class Medals(
    val gold: Int,
    val silver: Int,
    val bronze: Int
) {
    fun total(): Int = gold + silver
}

data class Olymbits(val raceData: RaceData) {

    fun playTurn(turnData: TurnData): String {
        error(turnData.games[raceData.playerIndex])
        val gameWithLeastMedals = turnData.indexOfGameWithLeastMedals(raceData.playerIndex)
        error("game with least medals: $gameWithLeastMedals")
        if (turnData.games[gameWithLeastMedals].raceTrack == "GAME_OVER") {
            return "RIGHT"
        }
        val raceTrack = turnData.games[gameWithLeastMedals].raceTrack
        val playerPosition = turnData.games[gameWithLeastMedals].getPlayerPosition(raceData.playerIndex)
        val distanceToHurtle = distanceToHurtle(raceTrack, playerPosition)
        return keyCommand(distanceToHurtle)
    }

    fun distanceToHurtle(raceTrack: String, position: Int): Int {
        return raceTrack.substring(position).indexOf("#") - 1
    }

    fun keyCommand(steps: Int): String = when (steps) {
        0 -> "UP"
        1 -> "LEFT"
        2 -> "DOWN"
        else -> "RIGHT"

    }

}

fun error(str: String) {
    System.err.println(str)
}
class PowerOfThor {
    fun direction(thorPosition: Point, lightPosition: Point): String {
        if (thorPosition.x < lightPosition.x && thorPosition.y > lightPosition.y) return "NE"
        if (thorPosition.x > lightPosition.x && thorPosition.y > lightPosition.y) return "NW"
        if (thorPosition.x < lightPosition.x && thorPosition.y < lightPosition.y) return "SE"
        if (thorPosition.x > lightPosition.x && thorPosition.y < lightPosition.y) return "SW"

        if (thorPosition.x < lightPosition.x) return "E"
        if (thorPosition.x > lightPosition.x) return "W"
        if (thorPosition.y < lightPosition.y) return "S"
        return "N"
    }

    fun move(thorPosition: Point, direction: String): Point = when (direction) {
        "N" -> Point(thorPosition.x, thorPosition.y - 1)
        "E" -> Point(thorPosition.x + 1, thorPosition.y)
        "S" -> Point(thorPosition.x, thorPosition.y + 1)
        "W" -> Point(thorPosition.x + 1, thorPosition.y)
        "NE" -> Point(thorPosition.x + 1, thorPosition.y - 1)
        "NW" -> Point(thorPosition.x - 1, thorPosition.y - 1)
        "SE" -> Point(thorPosition.x + 1, thorPosition.y + 1)
        "SW" -> Point(thorPosition.x - 1, thorPosition.y + 1)
        else -> Point(thorPosition.x, thorPosition.y)
    }

}

data class Point(
    val x: Int,
    val y: Int
)


import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val numberOfCols = input.nextInt()
    val numberOfRows = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }

    val map = mutableListOf<String>()
    for (i in 0 until numberOfRows) {
        map.add(input.nextLine())
    }

    val defenceMap = FloodFill.DefenceMap(map)
    val flood = FloodFill().flood(defenceMap)
    println(FloodFill().render(defenceMap, flood))
}

class FloodFill {

    fun flood(defenceMap: DefenceMap, maxSteps: Int = Integer.MAX_VALUE): Set<Flood> {
        /* BSF algorithm
        procedure BFS(G, root) is
            let Q be a queue
            label root as explored
            Q.enqueue(root)
            while Q is not empty do
                v := Q.dequeue()
                if v is the goal then
                    return v
                for all edges from v to w in G.adjacentEdges(v) do
                     if w is not labeled as explored then
                         label w as explored
                         w.parent := v
                         Q.enqueue(w)
        */
        val towers = defenceMap.towers()
        val queue = LinkedList<Flood>()
        queue.addAll(towers.map { Flood(it.x, it.y, it.id, 0) })
        val flood = mutableSetOf<Flood>()

        var steps = 0
        while (queue.isNotEmpty() && steps <= maxSteps) {
            val current = queue.remove()
            addFlood(flood, current)
            for (neighbor in current.neighbors(defenceMap)) {
                if (contains(flood, neighbor)) {
                    continue
                }
                queue.add(neighbor)
            }
            steps++
        }
        return flood
    }

    private fun addFlood(flood: MutableSet<Flood>, current: Flood) {
        if (contains(flood, current)) {
            updateFlood(flood, current)
        } else {
            flood.add(current)
        }
    }

    private fun updateFlood(flood: MutableSet<Flood>, current: Flood) {
        val existing = flood.first { it.x == current.x && it.y == current.y }
        if (existing.steps == current.steps && existing.id != current.id) {
            flood.remove(existing)
            flood.add(existing.copy(id = '+'))
        }
    }

    fun contains(flood: Set<Flood>, neighbor: Flood): Boolean {
        return flood.map { Pair(it.x, it.y) }.contains(Pair(neighbor.x, neighbor.y))
    }

    fun render(defenceMap: DefenceMap, flood: Set<Flood>, step: Int = Int.MAX_VALUE): String {
        val map = defenceMap.map.toMutableList()
        for (f in flood) {
            if (f.steps <= step) {
                map[f.y] = map[f.y].replaceRange(f.x, f.x + 1, f.id.toString())
            }
        }
        return map.joinToString("\n")
    }

    data class Flood(
        val x: Int,
        val y: Int,
        var id: Char,
        val steps: Int,
    ) {
        fun neighbors(defenceMap: DefenceMap): Set<Flood> {
            val neighbors = mutableSetOf<Flood>()
            // north
            if (y - 1 >= 0 && defenceMap.isVisitable(x, y - 1)) {
                neighbors.add(Flood(x, y - 1, id, steps + 1))
            }
            // south
            if (y + 1 < defenceMap.height && defenceMap.isVisitable(x, y + 1)) {
                neighbors.add(Flood(x, y + 1, id, steps + 1))
            }
            // west
            if (x - 1 >= 0 && defenceMap.isVisitable(x - 1, y)) {
                neighbors.add(Flood(x - 1, y, id, steps + 1))
            }
            // east
            if (x + 1 < defenceMap.width && defenceMap.isVisitable(x + 1, y)) {
                neighbors.add(Flood(x + 1, y, id, steps + 1))
            }
            return neighbors
        }
    }

    data class DefenceMap(
        val map: List<String>
    ) {
        val width = map.first().length
        val height = map.size

        fun towers(): List<Tower> {
            val towers = mutableListOf<Tower>()
            for (y in 0 until height) {
                for (x in 0 until width) {
                    if (!(map[y][x] == '.' || map[y][x] == '#')) {
                        towers.add(Tower(map[y][x], x, y))
                    }
                }
            }
            return towers
        }

        fun charAt(x: Int, y: Int): Char {
            return map[y][x]
        }

        fun isVisitable(x: Int, y: Int): Boolean {
            return map[y][x] == '.'
        }
    }

    data class Tower(
        val id: Char,
        val x: Int,
        val y: Int
    )


}
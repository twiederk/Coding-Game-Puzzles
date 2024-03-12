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
    for (i in 0 until numberOfRows) {
        val line = input.nextLine()
    }

    // Write an answer using println()
    // To debug: System.err.println("Debug messages...");

    println("answer")
}

class FloodFillExample {

    fun flood(defenceMap: DefenceMap, maxSteps: Int = Integer.MAX_VALUE): Set<Flood> {
        //    1  procedure BFS(G, root) is
//    2      let Q be a queue
//    3      label root as explored
//    4      Q.enqueue(root)
//    5      while Q is not empty do
//    6          v := Q.dequeue()
//    7          if v is the goal then
//    8              return v
//    9          for all edges from v to w in G.adjacentEdges(v) do
//    10              if w is not labeled as explored then
//    11                  label w as explored
//    12                  w.parent := v
//    13                  Q.enqueue(w)
        val towers = defenceMap.towers()
        val queue = LinkedList<Flood>()
        queue.addAll(towers.map { Flood(it.x, it.y, it.id, 0) })
        val flood = mutableSetOf<Flood>()

        var steps = 0
        while (queue.isNotEmpty() && steps < maxSteps) {
            val curr = queue.remove()
            flood.add(curr)
            for (neighbor in curr.neighbors(defenceMap)) {
                if (contains(flood, neighbor)) {
                    val floodNeighbor = flood.first { it.x == neighbor.x && it.y == neighbor.y }
                    if (floodNeighbor.steps == neighbor.steps) {
                        flood.remove(floodNeighbor)
                        flood.add(Flood(neighbor.x, neighbor.y, '+', neighbor.steps))
                    }
                    continue
                }
                queue.add(neighbor)
            }
            steps++
        }
        return flood
    }

    fun contains(flood: Set<Flood>, neighbor: Flood): Boolean {
        return flood.map { Pair(it.x, it.y) }.contains(Pair(neighbor.x, neighbor.y))
    }

    fun render(defenceMap: DefenceMap, flood: Set<Flood>): String {
        val map = defenceMap.map.toMutableList()
        for (f in flood) {
            map[f.y] = map[f.y].replaceRange(f.x, f.x + 1, f.id.toString())
        }
        return map.joinToString("\n")
    }

    data class Flood(
        val x: Int,
        val y: Int,
        val id: Char,
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
                    if (map[y][x] in 'A'..'Z') {
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
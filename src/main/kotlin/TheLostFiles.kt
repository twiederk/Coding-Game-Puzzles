import java.util.*

typealias Vertex = Int

fun main() {
    val input = Scanner(System.`in`)
    val numberOfEdges = input.nextInt()
    val edges = mutableListOf<MapGenerator.Edge>()
    for (i in 0 until numberOfEdges) {
        val n1 = input.nextInt()
        val n2 = input.nextInt()
        edges.add(MapGenerator.Edge(n1, n2))
    }

    val (continents, polygons) = MapGenerator().solve(edges)

    println("$continents $polygons")
}

class MapGenerator {
    fun solve(edges: List<Edge>): Edge {
        val continents = buildContinents(edges)
        val numberOfContinents = continents.size
        val numberOfPolygons = continents.sumOf { 2 + it.edges.size - it.vertices.size - 1 }

        return Edge(numberOfContinents, numberOfPolygons)
    }

    fun buildContinents(edges: List<Edge>): List<Continent> {
        val continents = mutableListOf<Continent>()
        for (edge in edges) {
            val continentsContainingEdge = getContinentsByEdge(continents, edge)
            when (continentsContainingEdge.size) {
                0 -> continents.add(Continent().apply { addEdge(edge) })
                1 -> continentsContainingEdge[0].addEdge(edge)
                2 -> {
                    continentsContainingEdge[0].addContinent(continentsContainingEdge[1])
                    continentsContainingEdge[0].addEdge(edge)
                    continents.remove(continentsContainingEdge[1])
                }
            }
        }
        return continents
    }

    fun getContinentsByEdge(continents: List<Continent>, edge: Edge): List<Continent> {
        return continents.filter { it.hasVertex(edge) }
    }

    class Continent {
        val edges = mutableSetOf<Edge>()
        val vertices = mutableSetOf<Vertex>()

        fun addEdge(edge: Edge) {
            edges.add(edge)
            vertices.add(edge.first)
            vertices.add(edge.second)
        }

        fun hasVertex(edge: Edge): Boolean {
            return vertices.contains(edge.first) || vertices.contains(edge.second)
        }

        fun addContinent(continent: Continent) {
            continent.edges.forEach { addEdge(it) }
        }
    }

    data class Edge(
        val first: Vertex,
        val second: Vertex
    ) {
        override fun toString(): String {
            return "$first -> $second"
        }
    }


}



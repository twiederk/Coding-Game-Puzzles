import DeathFirstSearchEpisode2.Link
import DeathFirstSearchEpisode2.Node
import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    /* val numberOfNodes = */ input.nextInt() // the total number of nodes in the level, including the gateways
    val numberOfLinks = input.nextInt() // the number of links
    val numberOfExitGateways = input.nextInt() // the number of exit gateways

    val nodes = mutableSetOf<Node>()
    val edges = mutableSetOf<Link>()
    for (i in 0 until numberOfLinks) {
        val node1 = Node(input.nextInt()) // N1 and N2 defines a link between these nodes
        val node2 = Node(input.nextInt())
        nodes.add(node1)
        nodes.add(node2)
        edges.add(Link(node1, node2))
    }
    val gateways = mutableSetOf<Node>()
    for (i in 0 until numberOfExitGateways) {
        val gateway = Node(input.nextInt()) // the index of a gateway node
        gateways.add(gateway)
    }

    val deathFirstSearchEpisode2 = DeathFirstSearchEpisode2(nodes, edges, gateways)

    // game loop
    while (true) {
        val agent = input.nextInt() // The index of the node on which the Bobnet agent is positioned this turn

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");
        val severLink: Link = deathFirstSearchEpisode2.severLink(Node(agent))


        // Example: 0 1 are the indices of the nodes you wish to sever the link between
        println("${severLink.first.data} ${severLink.second.data}")
    }
}

data class DeathFirstSearchEpisode2(
    val nodes: Set<Node>,
    val edges: MutableSet<Link>,
    val gateways: Set<Node>
) {

    init {
        System.err.println("nodes = $nodes")
        System.err.println("edges = $edges")
        System.err.println("gateways = $gateways")
    }

    fun severLink(agent: Node): Link {
        System.err.println("agent: $agent")
        // find path from agent to gateway
        val paths = gateways.mapNotNull { dijkstra(edges, agent, it) }
        System.err.println("number of paths: ${paths.size}")
        paths.forEach { System.err.println("$it ${it.parents().size} ${it.parents()}") }
        val path = paths.minBy { it.parents().size }

        // sever link contained in this path
        if (path.parent == null) {
            return Link(Node(0), Node(1))
        }
        edges.remove(Link(path, path.parent!!))
        edges.remove(Link(path.parent!!, path))
        return Link(path, path.parent!!)
    }

    private fun dijkstra(graph: Set<Link>, root: Node, goal: Node): Node? {
        val seen: MutableSet<Work> = mutableSetOf()
        val queue: PriorityQueue<Work> = PriorityQueue()
        queue.add(Work(root, 0))

        while (queue.isNotEmpty()) {
            val (currNode, distance) = queue.poll()
            if (currNode == goal) {
                return currNode
            }

            neighbors(currNode, graph)
                .filterNot { neighbor -> neighbor in seen.map { it.node } }
                .forEach { neighbor ->
                    neighbor.parent = currNode
                    val work = Work(neighbor, distance + 1)
                    queue.add(work)
                    seen.add(work)
                }
        }
        return null
    }


    fun neighbors(node: Node, graph: Set<Link>): Set<Node> {
        val neighbors = mutableSetOf<Node>()
        neighbors.addAll(graph.filter { it.first == node }.map { it.second })
        neighbors.addAll(graph.filter { it.second == node }.map { it.first })
        return neighbors
    }

    data class Node(
        val data: Int
    ) {
        var parent: Node? = null

        fun parents(): List<Node> {
            val parents = mutableListOf(this)
            var parent = this.parent
            while (parent != null) {
                parents.add(parent)
                parent = parent.parent
            }
            return parents
        }
    }

    data class Work(
        val node: Node,
        val distance: Int
    ) : Comparable<Work> {
        override fun compareTo(other: Work): Int = distance.compareTo(other.distance)
    }

    data class Link(
        val first: Node,
        val second: Node
    ) {
        override fun toString(): String {
            return "${first.data} -> ${second.data}"
        }
    }


}

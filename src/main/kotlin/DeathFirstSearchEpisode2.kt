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
        System.err.println("nodes = ${nodes}")
        System.err.println("edges = ${edges}")
        System.err.println("gateways = ${gateways}")
    }

    fun severLink(agent: Node): Link {
        System.err.println("agent: $agent")
        // find path from agent to gateway
        val path = bfs(edges, agent, gateways)
        // sever link contained in this path
        if (path?.parent == null) {
            return Link(Node(0), Node(1))
        }
        edges.remove(Link(path, path.parent!!))
        edges.remove(Link(path.parent!!, path))
        return Link(path, path.parent!!)
    }

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
    private fun bfs(graph: Set<Link>, root: Node, goal: Set<Node>): Node? {
        val queue: Queue<Node> = LinkedList()
        val seen: MutableSet<Node> = mutableSetOf()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val curr = queue.remove()
            if (goal.contains(curr)) {
                return curr
            }
            for (neighbor in neighbors(curr, graph)) {
                if (!seen.contains(neighbor)) {
                    seen.add(curr)
                    neighbor.parent = curr
                    queue.add(neighbor)
                }
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
        fun firstLink(): Link {
            // build a list of all parents
            // reverse the list
            // create link of first and second element
            TODO("Not yet implemented")
        }

        var parent: Node? = null
    }

    data class Link(
        val first: Node,
        val second: Node
    )


}

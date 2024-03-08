import java.util.*

typealias Node = Int
typealias Edge = Pair<Int, Int>

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val numberOfNodes = input.nextInt() // the total number of nodes in the level, including the gateways
    val numberOfLinks = input.nextInt() // the number of links
    val numberOfExitGateways = input.nextInt() // the number of exit gateways

    val nodes = mutableSetOf<Node>()
    val edges = mutableSetOf<Edge>()
    for (i in 0 until numberOfLinks) {
        val node1 = input.nextInt() // N1 and N2 defines a link between these nodes
        val node2 = input.nextInt()
        nodes.add(node1)
        nodes.add(node2)
        edges.add(Edge(node1, node2))
    }
    val gateways = mutableSetOf<Node>()
    for (i in 0 until numberOfExitGateways) {
        val gateway = input.nextInt() // the index of a gateway node
        gateways.add(gateway)
    }

    val deathFirstSearchEpisode1 = DeathFirstSearchEpisode1(nodes, edges, gateways)

    // game loop
    while (true) {
        val agent = input.nextInt() // The index of the node on which the Bobnet agent is positioned this turn

        // Write an action using println()
        // To debug: System.err.println("Debug messages...");
        val severLink: Edge = deathFirstSearchEpisode1.severLink(agent)


        // Example: 0 1 are the indices of the nodes you wish to sever the link between
        println("${severLink.first} ${severLink.second}")
    }
}

data class DeathFirstSearchEpisode1(
    val nodes: Set<Node>,
    val edges: Set<Edge>,
    val gateways: Set<Node>
) {

    init {
        System.err.println("nodes = ${nodes}")
        System.err.println("edges = ${edges}")
        System.err.println("gateways = ${gateways}")
    }

    fun severLink(agent: Node): Edge {
        System.err.println("agent: $agent")
        // find path from agent to gateway
        // sever link contained in this path
        return Edge(1, 2)
    }

}


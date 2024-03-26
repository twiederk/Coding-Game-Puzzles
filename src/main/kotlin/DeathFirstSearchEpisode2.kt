import DeathFirstSearchEpisode2.*
import java.util.*

//https://www.codingame.com/blog/dfs-agent-skynet/
/**
The agent can reach its desired gateway by the following algorithm each turn:

1. Try to reach a node from which more than 1 gateway is connected.
2. If the node in (1) isn’t available, try to reach a node connected to at least 1 gateway.
3. If the node in (2) isn’t available, try to reach a node from which a gateway is closest.
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

    val deathFirstSearchEpisode2 = DeathFirstSearchEpisode2(nodes, Links(edges), gateways)

    // game loop
    while (true) {
        val agent = input.nextInt() // The index of the node on which the Bobnet agent is positioned this turn

        val severLink: Link = deathFirstSearchEpisode2.severLink(Node(agent))

        // Example: 0 1 are the indices of the nodes you wish to sever the link between
        println("${severLink.first.data} ${severLink.second.data}")
    }
}

data class DeathFirstSearchEpisode2(
    val nodes: Set<Node>,
    val links: Links,
    val gateways: Set<Node>
) {

    init {
        System.err.println("nodes = $nodes")
        System.err.println("edges = $links")
        System.err.println("gateways = $gateways")
    }

    fun severLink(agent: Node): Link {
        System.err.println("agent: $agent")
        // find path from agent to gateway
        val paths = gateways.mapNotNull { dijkstra(links.edges, agent, it) }.map { Path(it.parents().reversed()) }

        val finalPath = finalPath(paths)

        // sever link contained in this path
        links.remove(finalPath.link)
        return finalPath.link
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

    fun shortestPaths(paths: List<Path>): List<Path> {
        val minParents = paths.minOfOrNull { it.size }
        return paths.filter { it.size == minParents }
    }

    fun finalPath(paths: List<Path>): Path {
        System.err.println("number of paths: ${paths.size}")
        paths.forEach(System.err::println)

        // when we have only one path, we select it
        if (paths.size == 1) {
            return paths.first()
        }

        // the paths build a graph on its own
        val shortestPaths = shortestPaths(paths)

        val agentGraph = AgentGraph(shortestPaths)

        // this graph is a tree with gateways as leaves
        // nodes which leads to more gateways are more important
        // the agent will move to the node with better position
        // we need to select the path with the node with the best position and sever the link there

        val maxLink = agentGraph.maxLink()
        return agentGraph.paths(maxLink).first()

    }

    fun stepsToNearestGateway(node: Node): Int {
        if (gateways.contains(node)) {
            return 0
        }
        val paths = gateways.map { gateway -> Path(dijkstra(links.edges, node, gateway)!!.parents()) }
        return paths.minOf { it.size } - 1
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
        val second: Node,
        val weight: Int = 1
    ) {
        override fun toString(): String {
            return "${first.data} -> ${second.data}"
        }
    }

    data class Path(
        val path: List<Node>,
    ) {
        val size = path.size
        val link = Link(path[path.size - 2], path[path.size - 1])
    }

    data class Links(
        val edges: MutableSet<Link>
    ) {
        fun remove(link: Link) {
            edges.remove(Link(link.first, link.second))
            edges.remove(Link(link.second, link.first))
        }
    }

    class AgentGraph(
        val paths: List<Path>
    ) {
        val links: List<Link>

        init {
            val pairs = paths.flatMap { it.path.windowed(2) }.groupingBy { it }.eachCount()
            links = pairs.map { (pair, count) ->
                Link(pair.first(), pair.last(), weight = count)
            }
        }

        fun maxLink(): Link {
            return links.maxBy { it.weight }
        }

        fun paths(link: Link): List<Path> {
            return paths.filter { it.path.contains(link.first) && it.path.contains(link.second) }
        }

    }

}


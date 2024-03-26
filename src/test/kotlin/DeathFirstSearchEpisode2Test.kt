import DeathFirstSearchEpisode2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeathFirstSearchEpisode2Test {

    private val test1 = DeathFirstSearchEpisode2(
        nodes = setOf(Node(1), Node(2), Node(0)),
        links = Links(mutableSetOf(Link(Node(1), Node(2)), Link(Node(1), Node(0)))),
        gateways = setOf(Node(2))
    )

    private val test2 = DeathFirstSearchEpisode2(
        nodes = setOf(
            Node(data = 2),
            Node(data = 6),
            Node(data = 9),
            Node(data = 7),
            Node(data = 0),
            Node(data = 8),
            Node(data = 1),
            Node(data = 3),
            Node(data = 5),
            Node(data = 4)
        ),
        links = Links(
            mutableSetOf(
                Link(Node(2), Node(6)),
                Link(Node(9), Node(7)),
                Link(Node(0), Node(7)),
                Link(Node(9), Node(8)),
                Link(Node(8), Node(2)),
                Link(Node(7), Node(1)),
                Link(Node(9), Node(2)),
                Link(Node(3), Node(1)),
                Link(Node(2), Node(5)),
                Link(Node(0), Node(8)),
                Link(Node(4), Node(1)),
                Link(Node(9), Node(1)),
                Link(Node(0), Node(9)),
                Link(Node(2), Node(1)),
            )
        ),
        gateways = setOf(Node(data = 3), Node(data = 4), Node(data = 5), Node(data = 6))
    )

    private val test3 = DeathFirstSearchEpisode2(
        nodes = setOf(
            Node(data = 0),
            Node(data = 1),
            Node(data = 2),
            Node(data = 3),
            Node(data = 4),
            Node(data = 5),
            Node(data = 6),
            Node(data = 7),
            Node(data = 8),
            Node(data = 9),
            Node(data = 10),
            Node(data = 11),
            Node(data = 12),
            Node(data = 13),
        ),
        links = Links(
            mutableSetOf(
                Link(Node(0), Node(1)),
                Link(Node(0), Node(5)),
                Link(Node(1), Node(2)),
                Link(Node(2), Node(3)),
                Link(Node(2), Node(4)),
                Link(Node(3), Node(4)),
                Link(Node(4), Node(6)),
                Link(Node(5), Node(9)),
                Link(Node(5), Node(10)),
                Link(Node(6), Node(11)),
                Link(Node(6), Node(12)),
                Link(Node(6), Node(13)),
                Link(Node(6), Node(7)),
                Link(Node(7), Node(8)),
                Link(Node(7), Node(13)),
                Link(Node(9), Node(10)),
                Link(Node(10), Node(11)),
                Link(Node(11), Node(12)),
                Link(Node(12), Node(13)),
            )
        ),
        gateways = setOf(Node(data = 3), Node(data = 6), Node(data = 8), Node(data = 9))
    )

    // 0 - 1 - 2
    @Test
    fun should_return_link_to_sever_when_graph_is_test1() {

        // act
        val result = test1.severLink(Node(1))

        // assert
        assertThat(result).isEqualTo(Link(Node(1), Node(2)))
    }

    // 0 - 1 - 2
    @Test
    fun should_return_neighbors_of_node_0_when_graph_is_test1() {

        // act
        val neighbors = test1.neighbors(Node(0), test1.links.edges)

        // assert
        assertThat(neighbors).containsExactly(Node(1))
    }

    // 0 - 1 - 2
    @Test
    fun should_return_neighbors_of_node_1_when_graph_is_test1() {

        // act
        val neighbors = test1.neighbors(Node(1), test1.links.edges)

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(Node(0), Node(2))
    }

    // 0 - 1 - 2
    @Test
    fun should_return_neighbors_of_node_2_when_graph_is_test1() {

        // act
        val neighbors = test1.neighbors(Node(2), test1.links.edges)

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(Node(1))
    }

    @Test
    fun should_select_path_which_runs_via_same_node() {
        // arrange
        // 9 -> 1 -> 4
        // 9 -> 2 -> 5
        // 9 -> 2 -> 6
        val path1 = Path(listOf(Node(9), Node(1), Node(4)))
        val path2 = Path(listOf(Node(9), Node(2), Node(5)))
        val path3 = Path(listOf(Node(9), Node(2), Node(6)))
        test2.links.remove(Link(Node(1), Node(3)))

        // act
        val finalPath = test2.finalPath(listOf(path1, path2, path3))

        // assert
        assertThat(finalPath).isEqualTo(path2)
    }

    @Test
    fun should_filter_all_paths_with_shortest_list_of_parents() {
        // arrange
        val path1 = Path(listOf(Node(4), Node(1), Node(9)))
        val path2 = Path(listOf(Node(5), Node(2)))
        val path3 = Path(listOf(Node(6), Node(2)))
        val path4 = Path(listOf(Node(6), Node(2), Node(9), Node(10)))
        val path5 = Path(listOf(Node(6), Node(2), Node(9), Node(10)))
        val path6 = Path(listOf(Node(6), Node(2), Node(9), Node(10), Node(11)))

        // act
        val shortestPaths = test1.shortestPaths(listOf(path1, path2, path3, path4, path5, path6))

        // assert
        assertThat(shortestPaths).contains(path2, path3)
    }

    @Test
    fun should_create_agent_graph() {
        // arrange
        val path1 = Path(listOf(Node(9), Node(1), Node(4)))
        val path2 = Path(listOf(Node(9), Node(2), Node(5)))
        val path3 = Path(listOf(Node(9), Node(2), Node(6)))

        // act
        val agentGraph = AgentGraph(listOf(path1, path2, path3))

        // assert
        assertThat(agentGraph.paths).hasSize(3)
        assertThat(agentGraph.links).hasSize(5)
    }

    @Test
    fun should_return_link_with_highest_weight() {
        // arrange
        val path1 = Path(listOf(Node(9), Node(1), Node(4)))
        val path2 = Path(listOf(Node(9), Node(2), Node(5)))
        val path3 = Path(listOf(Node(9), Node(2), Node(6)))
        val agentGraph = AgentGraph(listOf(path1, path2, path3))

        // act
        val result = agentGraph.maxLink()

        // assert
        assertThat(result).isEqualTo(Link(Node(9), Node(2), weight = 2))
    }

    @Test
    fun should_return_paths_containing_link() {
        // arrange
        val path1 = Path(listOf(Node(9), Node(1), Node(4)))
        val path2 = Path(listOf(Node(9), Node(2), Node(5)))
        val path3 = Path(listOf(Node(9), Node(2), Node(6)))
        val agentGraph = AgentGraph(listOf(path1, path2, path3))

        // act
        val paths = agentGraph.paths(Link(Node(9), Node(2)))

        // assert
        assertThat(paths).containsExactlyInAnyOrder(path2, path3)
    }

    @Test
    fun should_sever_link_with_lowest_weight() {
        // arrange

        // act
        val severLink = test3.severLink(Node(0))

        // assert
        assertThat(severLink).isEqualTo(Link(Node(5), Node(9)))
    }
}
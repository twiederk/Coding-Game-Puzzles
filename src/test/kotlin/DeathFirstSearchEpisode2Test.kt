import DeathFirstSearchEpisode2.Link
import DeathFirstSearchEpisode2.Node
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeathFirstSearchEpisode2Test {

    private val test1 = DeathFirstSearchEpisode2(
        nodes = setOf(Node(1), Node(2), Node(0)),
        edges = mutableSetOf(Link(Node(1), Node(2)), Link(Node(1), Node(0))),
        gateways = setOf(Node(2))
    )

    // 0 - 1 - 2
    @Test
    fun should_return_link_to_sever_when_graph_is_test1() {

        // act
        val result = test1.severLink(Node(1))

        // assert
        assertThat(result).isEqualTo(Link(Node(2), Node(1)))
    }

    // 0 - 1 - 2
    @Test
    fun should_return_neighbors_of_node_0_when_graph_is_test1() {

        // act
        val neighbors = test1.neighbors(Node(0), test1.edges)

        // assert
        assertThat(neighbors).containsExactly(Node(1))
    }

    // 0 - 1 - 2
    @Test
    fun should_return_neighbors_of_node_1_when_graph_is_test1() {

        // act
        val neighbors = test1.neighbors(Node(1), test1.edges)

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(Node(0), Node(2))
    }

    // 0 - 1 - 2
    @Test
    fun should_return_neighbors_of_node_2_when_graph_is_test1() {

        // act
        val neighbors = test1.neighbors(Node(2), test1.edges)

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(Node(1))
    }

    @Test
    fun should_select_path_which_runs_via_same_node() {
        // arrange
        // 4 -> 1 -> 9 (agent)
        // 5 -> 2 -> 9 (agent)
        // 6 -> 2 -> 9 (agent)
        val path1 = Node(4).apply { parent = Node(1).apply { parent = Node(9) } }
        val path2 = Node(5).apply { parent = Node(2).apply { parent = Node(9) } }
        val path3 = Node(6).apply { parent = Node(2).apply { parent = Node(9) } }

        // act
        val finalPath = test1.finalPath(listOf(path1, path2, path3))

        // assert
        assertThat(finalPath).isEqualTo(path2)
    }

    @Test
    fun should_filter_all_paths_with_shortest_list_of_parents() {
        // arrange
        val path1 = Node(4).apply { parent = Node(1).apply { parent = Node(9) } }
        val path2 = Node(5).apply { parent = Node(2) }
        val path3 = Node(6).apply { parent = Node(2) }
        val path4 = Node(6).apply { parent = Node(2).apply { parent = Node(9).apply { parent = Node(10) } } }
        val path5 = Node(6).apply { parent = Node(2).apply { parent = Node(9).apply { parent = Node(10) } } }
        val path6 = Node(6).apply {
            parent = Node(2).apply { parent = Node(9).apply { parent = Node(10).apply { parent = Node(11) } } }
        }

        // act
        val shortestPaths = test1.shortestPaths(listOf(path1, path2, path3, path4, path5, path6))

        // assert
        assertThat(shortestPaths).contains(path2, path3)

    }
}
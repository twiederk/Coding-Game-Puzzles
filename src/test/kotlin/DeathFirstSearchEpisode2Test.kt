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
    fun should_return_first_link_of_path() {
        // arrange
        val node1 = Node(1)
        val node2 = Node(2).apply { parent = node1 }
        val node3 = Node(3).apply { parent = node2 }
        val node4 = Node(4).apply { parent = node3 }

        // act
        val firstLink = node4.firstLink()

        // assert
        assertThat(firstLink).isEqualTo(Link(node1, node2))
    }
}
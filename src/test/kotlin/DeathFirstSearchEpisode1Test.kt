import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

// https://www.codingame.com/ide/puzzle/death-first-search-episode-1

class DeathFirstSearchEpisode1Test {

    // 1 - 2 - 3
    @Test
    fun should_build_graph_of_test1() {
        // arrange
        val deathFirstSearchEpisode1 = DeathFirstSearchEpisode1(
            nodes = setOf(1, 2, 0),
            edges = setOf(Edge(1, 2), Edge(1, 0)),
            gateways = setOf(2)
        )

        // act
        val result = deathFirstSearchEpisode1.severLink(1)

        // assert
        assertThat(result).isEqualTo(Edge(1, 2))
    }

}
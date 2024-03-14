import MapGenerator.Continent
import MapGenerator.Edge
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TheLostFilesMapGeneratorTest {

    @Test
    fun should_solve_continents_and_polygons_when_small_example_is_given() {

        // act
        val (continents, polygons) = MapGenerator().solve(TheLostFilesTestContinents.SMALL_EXAMPLE)

        // assert
        assertThat(continents).isEqualTo(2)
        assertThat(polygons).isEqualTo(4)
    }

    @Test
    fun should_solve_continents_and_polygons_when_single_continent_with_big_tiles_is_given() {

        // act
        val (continents, polygons) = MapGenerator().solve(TheLostFilesTestContinents.SINGLE_CONTINENT_WITH_BIG_TILES)

        // assert
        assertThat(continents).isEqualTo(1)
        assertThat(polygons).isEqualTo(16)
    }

    @Test
    fun should_solve_continents_and_polygons_when_many_islands_is_given() {

        // act
        val (continents, polygons) = MapGenerator().solve(TheLostFilesTestContinents.MANY_ISLANDS)

        // assert
        assertThat(continents).isEqualTo(10)
        assertThat(polygons).isEqualTo(20)
    }

    @Test
    fun should_solve_continents_and_polygons_when_complex_map_is_given() {

        // act
        val (continents, polygons) = MapGenerator().solve(TheLostFilesTestContinents.COMPLEX_MAP)

        // assert
        assertThat(continents).isEqualTo(3)
        assertThat(polygons).isEqualTo(130)
    }

    @Test
    fun should_build_continents_when_small_example_is_given() {

        // act
        val continents = MapGenerator().buildContinents(TheLostFilesTestContinents.SMALL_EXAMPLE)

        // assert
        assertThat(continents).hasSize(2)

    }

    @Test
    fun should_return_continent_when_vertex_is_part_of_continent() {
        // arrange
        val continents = listOf(
            Continent().apply {
                addEdge(Edge(3, 9))
                addEdge(Edge(3, 6))
                addEdge(Edge(6, 9))
            }
        )

        // act
        val continentsByEdge = MapGenerator().getContinentsByEdge(continents, Edge(3, 9))

        // assert
        assertThat(continentsByEdge).containsExactly(continents[0])
    }

    @Test
    fun should_return_null_when_vertex_is_not_part_any_continent() {
        // arrange
        val continents = listOf(
            Continent().apply {
                addEdge(Edge(3, 9))
                addEdge(Edge(3, 6))
                addEdge(Edge(6, 9))
            }
        )

        // act
        val continentsWithEdge = MapGenerator().getContinentsByEdge(continents, Edge(1, 2))

        // assert
        assertThat(continentsWithEdge).isEmpty()
    }

}
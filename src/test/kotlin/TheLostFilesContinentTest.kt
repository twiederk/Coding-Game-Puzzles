import MapGenerator.Continent
import MapGenerator.Edge
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TheLostFilesContinentTest {

    private val edge3to9 = Edge(3, 9)
    private val edge6to3 = Edge(6, 3)
    private val edge9to6 = Edge(9, 6)
    private val edge9to5 = Edge(9, 5)
    private val edge6to5 = Edge(6, 5)

    private val continentOnePolygon = Continent().apply {
        addEdge(edge3to9)
        addEdge(edge6to3)
        addEdge(edge9to6)
    }

    private val continentTwoPolygons = Continent().apply {
        addEdge(edge3to9)
        addEdge(edge6to3)
        addEdge(edge9to6)
        addEdge(edge9to5)
        addEdge(edge6to5)
    }


    @Test
    fun should_add_edge_to_continent() {
        // arrange
        val continent = Continent()
        val edge = Edge(1, 2)

        // act
        continent.addEdge(edge)

        // assert
        assertThat(continent.edges).containsExactly(edge)
        assertThat(continent.vertices).containsExactly(1, 2)

    }

    @Test
    fun should_return_true_when_continent_has_vertex() {
        // arrange
        val edge = Edge(1, 2)
        val continent = Continent().apply { addEdge(edge) }

        // act
        val hasVertex = continent.hasVertex(edge)

        // assert
        assertThat(hasVertex).isTrue()
    }

    @Test
    fun should_add_edges_and_vertices_to_continent_when_other_continent_is_given() {
        // arrange
        val continent1 = Continent().apply { addEdge(Edge(1, 2)) }
        val continent2 = Continent().apply { addEdge(Edge(3, 4)) }

        // act
        continent1.addContinent(continent2)

        // assert
        assertThat(continent1.vertices).containsExactly(1, 2, 3, 4)
        assertThat(continent1.edges).containsExactly(Edge(1, 2), Edge(3, 4))
    }

    /*
        //         9
        //        /|
        //      3  |
        //       \|
        //        6
        @Test
        fun should_connect_edges_to_build_graph_when_continent_has_one_polygon() {

            // act
            val graph = continentOnePolygon.buildGraph()

            // assert
            assertThat(graph).hasSize(3)
            assertThat(graph[3]).contains(9, 6)
            assertThat(graph[6]).contains(3, 9)
            assertThat(graph[9]).contains(3, 6)
        }

        //         9
        //        /|\
        //      3  | 5
        //       \|/
        //        6
        @Test
        fun should_connect_edges_to_build_graph_when_continent_has_two_polygons() {

            // act
            continentTwoPolygons.buildGraph()

            // assert
            assertThat(continentTwoPolygons.graph).hasSize(4)
            assertThat(continentTwoPolygons.graph[3]).contains(9, 6)
            assertThat(continentTwoPolygons.graph[6]).contains(3, 9, 5)
            assertThat(continentTwoPolygons.graph[9]).contains(3, 6, 5)
            assertThat(continentTwoPolygons.graph[5]).contains(6, 9)
        }

        @Test
        fun should_create_one_work_when_vertex_has_one_destination() {
            // arrange
            val graph = mutableMapOf<Int, Set<Int>>()
            graph[1] = setOf(2)

            // act
            val newWork = Continent().newWork(Work(listOf(1)), graph)

            // assert
            assertThat(newWork).containsExactly(Work(listOf(1, 2)))
        }

        @Test
        fun should_create_two_work_when_vertex_has_two_destinations() {
            // arrange
            val graph = mutableMapOf<Int, Set<Int>>()
            graph[1] = setOf(2, 3)

            // act
            val newWork = Continent().newWork(Work(listOf(1)), graph)

            // assert
            assertThat(newWork).containsExactly(Work(listOf(1, 2)), Work(listOf(1, 3)))
        }

        @Test
        fun should_not_create_work_when_destination_is_previous_vertex() {

            // act
            val newWork = Continent().newWork(Work(listOf(3, 6)), continentOnePolygon.buildGraph())

            // assert
            assertThat(newWork).containsExactly(Work(listOf(3, 6, 9)))
        }

        @Test
        fun should_not_create_work_when_polygon_is_completed() {

            // act
            val newWork = Continent().newWork(Work(listOf(3, 6, 9)), continentOnePolygon.buildGraph())

            // assert
            assertThat(newWork).isEmpty()
        }

        @Test
        fun should_return_true_when_polygon_is_completed() {

            // act
            val result = Continent().isPolygon(Work(listOf(3, 6, 9)), continentOnePolygon.buildGraph())

            // assert
            assertThat(result).isTrue()
        }

        @Test
        fun should_return_false_when_work_has_only_two_vertices() {

            // act
            val result = Continent().isPolygon(Work(listOf(3, 6)), continentOnePolygon.buildGraph())

            // assert
            assertThat(result).isFalse()
        }

        //         9
        //        /|
        //      3  |
        //       \|
        //        6
        @Test
        fun should_build_polygon_when_continent_has_only_one_polygon() {
            // arrange
            continentOnePolygon.buildGraph()

            // act
            val polygons = continentOnePolygon.buildPolygons()

            // assert
            assertThat(polygons).hasSize(1)
            assertThat(polygons.first()).isEqualTo(Polygon(setOf(3, 6, 9)))
        }


        //         9
        //        /|\
        //      3  | 5
        //       \|/
        //        6
        @Test
        fun should_build_polygons_when_continent_has_only_two_polygons() {
            // arrange
            continentTwoPolygons.buildGraph()

            // act
            val polygons = continentTwoPolygons.buildPolygons()

            // assert
            assertThat(polygons).hasSize(2)
            assertThat(polygons.first()).isEqualTo(Polygon(setOf(3, 6, 9)))
            assertThat(polygons.last()).isEqualTo(Polygon(setOf(5, 6, 9)))
        }

        @Test
        fun should_build_polygons_when_small_example_is_given() {
            // arrange
            val continent = MapGenerator().buildContinents(TestContinents.SMALL_EXAMPLE).first()
            continent.buildGraph()

            // act
            val polygons = continent.buildPolygons()

            // assert
            assertThat(polygons).hasSize(3)
            assertThat(polygons).containsExactlyInAnyOrder(
                Polygon(setOf(1, 2, 4, 7)),
                Polygon(setOf(0, 4, 7, 8, 10)),
                Polygon(setOf(5, 8, 10)),
            )
        }

        @Test
        fun should_build_polygons_when_single_continent_with_big_tiles_is_given() {
            // arrange
            val continent = MapGenerator().buildContinents(TestContinents.SINGLE_CONTINENT_WITH_BIG_TILES).first()
            continent.buildGraph()

            // act
            val polygons = continent.buildPolygons()

            // assert
            assertThat(polygons).hasSize(16)
        }
    */

}
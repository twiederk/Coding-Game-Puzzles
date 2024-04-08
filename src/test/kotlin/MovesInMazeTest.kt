import MovesInMaze.Flood
import MovesInMaze.Point2D
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovesInMazeTest {

    private val test1 = listOf(
        "##########",
        "#S.......#",
        "##.#####.#",
        "##.#.....#",
        "##########"
    )

    @Test
    fun should_solve_test_1() {

        // act
        val result = MovesInMaze().solve(test1)

        // assert
        assertThat(result).isEqualTo(
            """
            ##########
            #01234567#
            ##2#####8#
            ##3#DCBA9#
            ##########
        """.trimIndent()
        )
    }

    @Test
    fun should_find_start_position() {

        // act
        val start = MovesInMaze().start(test1)

        // assert
        assertThat(start).isEqualTo(Point2D(1, 1))
    }


    @Test
    fun should_flood_fill_maze() {

        // act
        val flood = MovesInMaze().floodfill(test1, Point2D(1, 1))

        // assert
//        ##########
//        #01234567#
//        ##2#####8#
//        ##3#DCBA9#
//        ##########
        assertThat(flood).containsExactly(
            Flood(Point2D(1, 1), 0),
            Flood(Point2D(2, 1), 1),
            Flood(Point2D(3, 1), 2),
            Flood(Point2D(4, 1), 3),
            Flood(Point2D(5, 1), 4),
            Flood(Point2D(6, 1), 5),
            Flood(Point2D(7, 1), 6),
//            Flood(Point2D(1, 2), 2),
//            Flood(Point2D(1, 3), 3),
        )
    }

    //    "##########",
//    "#S.......#",
//    "##.#####.#",
//    "##.#.....#",
//    "##########"
    @Test
    fun should_return_neighbors_of_pos_1_1() {
        // arrange

        // act
        val neighbors = Point2D(1, 1).neighbors(test1)

        // assert
        assertThat(neighbors).containsExactly(
            Point2D(2, 1)
        )
    }

}
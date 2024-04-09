import MovesInMaze.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovesInMazeTest {

    private val test1 = Maze(
        listOf(
            "##########",
            "#S.......#",
            "##.#####.#",
            "##.#.....#",
            "##########"
        )
    )
    private val flood1 = setOf(
        Flood(Point2D(1, 1), 0),
        Flood(Point2D(2, 1), 1),
        Flood(Point2D(3, 1), 2),
        Flood(Point2D(2, 2), 2),
        Flood(Point2D(2, 3), 3),
        Flood(Point2D(4, 1), 3),
        Flood(Point2D(5, 1), 4),
        Flood(Point2D(6, 1), 5),
        Flood(Point2D(7, 1), 6),
        Flood(Point2D(8, 1), 7),
        Flood(Point2D(8, 2), 8),
        Flood(Point2D(8, 3), 9),
        Flood(Point2D(7, 3), 10),
        Flood(Point2D(6, 3), 11),
        Flood(Point2D(5, 3), 12),
        Flood(Point2D(4, 3), 13),
    )

    private val test3 = Maze(
        listOf(
            "#.########",
            "#.##..####",
            "..##..#...",
            "####..#S##",
            "#....#####",
        )
    )


    @Test
    fun should_solve_test_3() {

        // act
        val result = MovesInMaze().solve(test3)

        // assert
        assertThat(result).isEqualTo(
            """
            #7########
            #6##EF####
            45##DE#123
            ####CD#0##
            #89AB#####
        """.trimIndent()
        )
    }

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
        val start = test1.start()

        // assert
        assertThat(start).isEqualTo(Point2D(1, 1))
    }


    @Test
    fun should_flood_fill_maze() {

        // act
        val flood = MovesInMaze().floodfill(test1, Point2D(1, 1))

        // assert
        // ##########
        // #01234567#
        // ##2#####8#
        // ##3#DCBA9#
        // ##########
        assertThat(flood).containsExactlyInAnyOrder(
            Flood(Point2D(1, 1), 0),
            Flood(Point2D(2, 1), 1),
            Flood(Point2D(3, 1), 2),
            Flood(Point2D(2, 2), 2),
            Flood(Point2D(2, 3), 3),
            Flood(Point2D(4, 1), 3),
            Flood(Point2D(5, 1), 4),
            Flood(Point2D(6, 1), 5),
            Flood(Point2D(7, 1), 6),
            Flood(Point2D(8, 1), 7),
            Flood(Point2D(8, 2), 8),
            Flood(Point2D(8, 3), 9),
            Flood(Point2D(7, 3), 10),
            Flood(Point2D(6, 3), 11),
            Flood(Point2D(5, 3), 12),
            Flood(Point2D(4, 3), 13),
        )
    }

    @Test
    fun should_return_neighbors_of_pos_1_1() {

        // act
        val neighbors = test1.neighbors(Point2D(1, 1))

        // assert
        assertThat(neighbors).containsExactly(
            Point2D(2, 1)
        )
    }

    @Test
    fun should_render_flooded_maze() {

        // act
        val floodedMaze = MovesInMaze().render(test1, flood1)

        // assert
        assertThat(floodedMaze).isEqualTo(
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
    fun should_convert_4_steps_to_step_sign_4() {

        // act
        val stepSign = MovesInMaze().stepSign(4)

        // assert
        assertThat(stepSign).isEqualTo('4')
    }

    @Test
    fun should_convert_10_steps_to_step_sign_A() {

        // act
        val stepSign = MovesInMaze().stepSign(10)

        // assert
        assertThat(stepSign).isEqualTo('A')
    }

    @Test
    fun should_convert_35_steps_to_step_sign_Z() {

        // act
        val stepSign = MovesInMaze().stepSign(35)

        // assert
        assertThat(stepSign).isEqualTo('Z')
    }

    @Test
    fun should_norm_x_when_too_large() {
        // arrange

        // act
        val result = test1.getSafeX(10)

        // assert
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun should_norm_x_when_too_small() {
        // arrange

        // act
        val result = test1.getSafeX(-1)

        // assert
        assertThat(result).isEqualTo(9)
    }

    @Test
    fun should_keep_x_when_on_maze() {
        // arrange

        // act
        val result = test1.getSafeX(5)

        // assert
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun should_norm_y_when_too_large() {
        // arrange

        // act
        val result = test1.getSafeY(5)

        // assert
        assertThat(result).isEqualTo(0)
    }


    @Test
    fun should_norm_y_when_too_small() {
        // arrange

        // act
        val result = test1.getSafeY(-1)

        // assert
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun should_keep_y_when_on_maze() {
        // arrange

        // act
        val result = test1.getSafeY(3)

        // assert
        assertThat(result).isEqualTo(3)
    }

}
import PaperLabyrinth.Point2D
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaperLabyrinthTest {

    private val test1 = PaperLabyrinth(
        start = Point2D(0, 0),
        rabbit = Point2D(5, 0),
        labyrinth = listOf("75555d")
    )

    private val test2 = PaperLabyrinth(
        start = Point2D(0, 0),
        rabbit = Point2D(1, 1),
        labyrinth = listOf(
            "e65c",
            "abea",
            "2519",
            "355d",
        )
    )

    private val test3 = PaperLabyrinth(
        start = Point2D(0, 0),
        rabbit = Point2D(1, 3),
        labyrinth = listOf(
            "75ce",
            "6598",
            "2758",
            "b759",
        )
    )

    private val test4 = PaperLabyrinth(
        start = Point2D(0, 0),
        rabbit = Point2D(3, 5),
        labyrinth = listOf(
            "7554e",
            "6459b",
            "a3c2c",
            "3caaa",
            "6119a",
            "35d39",
        )
    )

    @Test
    fun should_find_neighbors_of_0() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('0')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
            point + Point2D.RIGHT,
            point + Point2D.DOWN,
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_1() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('1')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
            point + Point2D.RIGHT,
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_2() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('2')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.RIGHT,
            point + Point2D.DOWN,
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_3() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('3')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.RIGHT,
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_4() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('4')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
            point + Point2D.RIGHT,
            point + Point2D.DOWN,
        )
    }

    @Test
    fun should_find_neighbors_of_5() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('5')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
            point + Point2D.RIGHT,
        )
    }

    @Test
    fun should_find_neighbors_of_6() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('6')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.RIGHT,
            point + Point2D.DOWN,
        )
    }

    @Test
    fun should_find_neighbors_of_7() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('7')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.RIGHT,
        )
    }

    @Test
    fun should_find_neighbors_of_8() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('8')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
            point + Point2D.DOWN,
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_9() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('9')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_a() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('a')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.DOWN,
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_b() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('b')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.TOP,
        )
    }

    @Test
    fun should_find_neighbors_of_c() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('c')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
            point + Point2D.DOWN,
        )
    }

    @Test
    fun should_find_neighbors_of_d() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('d')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.LEFT,
        )
    }

    @Test
    fun should_find_neighbors_of_e() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('e')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            point + Point2D.DOWN,
        )
    }

    @Test
    fun should_find_neighbors_of_f() {

        // arrange
        val point = Point2D(1, 1)

        // act
        val neighbors = point.neighbors('f')

        // assert
        assertThat(neighbors).isEmpty()
    }

    @Test
    fun should_return_wall_of_cell() {
        // act
        val wall = test1.wall(Point2D(0, 0))

        // assert
        assertThat(wall).isEqualTo('7')
    }

    @Test
    fun should_find_steps_to_rabbit_for_test1() {

        // act
        val stepsToRabbit = test1.bfs(test1.start, test1.rabbit)

        // assert
        assertThat(stepsToRabbit).isEqualTo(5)

    }

    @Test
    fun should_find_steps_to_exit_for_test1() {

        // act
        val stepsToRabbit = test1.bfs(test1.rabbit, test1.start)

        // assert
        assertThat(stepsToRabbit).isEqualTo(5)
    }

    @Test
    fun should_solve_test1() {

        // act
        val (stepsToRabbit, stepsToExit) = test1.solve()

        // assert
        assertThat(stepsToRabbit).isEqualTo(5)
        assertThat(stepsToExit).isEqualTo(5)
    }

    @Test
    fun should_solve_test2() {

        // act
        val (stepsToRabbit, stepsToExit) = test2.solve()

        // assert
        assertThat(stepsToRabbit).isEqualTo(10)
        assertThat(stepsToExit).isEqualTo(10)
    }

    @Test
    fun should_solve_test3() {

        // act
        val (stepsToRabbit, stepsToExit) = test3.solve()

        // assert
        assertThat(stepsToRabbit).isEqualTo(12)
        assertThat(stepsToExit).isEqualTo(8)
    }

    @Test
    fun should_solve_test4() {

        // act
        val (stepsToRabbit, stepsToExit) = test4.solve()

        // assert
        assertThat(stepsToRabbit).isEqualTo(18)
        assertThat(stepsToExit).isEqualTo(8)
    }

}
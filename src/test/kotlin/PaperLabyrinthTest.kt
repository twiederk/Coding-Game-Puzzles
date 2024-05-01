import PaperLabyrinth.Point2D
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaperLabyrinthTest {

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

}
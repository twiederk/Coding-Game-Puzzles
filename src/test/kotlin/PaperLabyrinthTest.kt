import PaperLabyrinth.Point2D
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PaperLabyrinthTest {

    @Test
    fun should_find_neighbors_of_0() {

        // act
        val neighbors = Point2D(1, 1).next('0')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            Point2D(0, 1),
            Point2D(1, 0),
            Point2D(1, 2),
            Point2D(2, 1)
        )
    }

    @Test
    fun should_find_neighbors_of_1() {

        // act
        val neighbors = Point2D(1, 1).next('1')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            Point2D(0, 1),
            Point2D(1, 0),
            Point2D(2, 1)
        )
    }

    @Test
    fun should_find_neighbors_of_2() {

        // act
        val neighbors = Point2D(1, 1).next('2')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            Point2D(1, 0),
            Point2D(1, 2),
            Point2D(2, 1)
        )
    }

    @Test
    fun should_find_neighbors_of_3() {

        // act
        val neighbors = Point2D(1, 1).next('3')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            Point2D(1, 0),
            Point2D(2, 1)
        )
    }

    @Test
    fun should_find_neighbors_of_4() {

        // act
        val neighbors = Point2D(1, 1).next('4')

        // assert
        assertThat(neighbors).containsExactlyInAnyOrder(
            Point2D(0, 1),
            Point2D(1, 2),
            Point2D(2, 1)
        )
    }

}
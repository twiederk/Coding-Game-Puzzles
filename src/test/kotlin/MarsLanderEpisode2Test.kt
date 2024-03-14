import MarsLanderEpisode2.Point2D
import MarsLanderEpisode2.Surface
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MarsLanderEpisode2Test {
    @Test
    fun should_return_start_and_end_coords_of_landing_area() {
        // arrange
        val points = listOf(
            Point2D(0, 1500),
            Point2D(1000, 2000),
            Point2D(2000, 500),
            Point2D(3500, 500),
            Point2D(5000, 1500),
            Point2D(6999, 1000),
        )
        val surface = Surface(points)


        // act
        val landingArea = surface.landingArea()

        // assert
        assertThat(landingArea).isEqualTo(Pair(Point2D(2000, 500), Point2D(3500, 500)))
    }
}

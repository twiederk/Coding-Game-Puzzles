import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PowerOfThorTest {

    @Test
    fun should_return_direction_EAST() {

        // arrange
        val thorPosition = Point(5, 4)
        val lightPosition = Point(31, 4)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("E")
    }

    @Test
    fun should_return_direction_WEST() {

        // arrange
        val thorPosition = Point(1, 1)
        val lightPosition = Point(0, 1)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("W")
    }

    @Test
    fun should_return_direction_NORTH() {

        // arrange
        val thorPosition = Point(31, 17)
        val lightPosition = Point(31, 4)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("N")
    }

    @Test
    fun should_return_direction_SOUTH() {

        // arrange
        val thorPosition = Point(1, 1)
        val lightPosition = Point(1, 2)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("S")
    }

    @Test
    fun should_return_direction_NORTH_EAST() {
        // arrange
        val thorPosition = Point(1, 1)
        val lightPosition = Point(2, 0)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("NE")
    }

    @Test
    fun should_return_direction_NORTH_WEST() {
        // arrange
        val thorPosition = Point(1, 1)
        val lightPosition = Point(0, 0)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("NW")
    }

    @Test
    fun should_return_direction_SOUTH_EAST() {
        // arrange
        val thorPosition = Point(1, 1)
        val lightPosition = Point(2, 2)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("SE")
    }

    @Test
    fun should_return_direction_SOUTH_WEST() {
        // arrange
        val thorPosition = Point(1, 1)
        val lightPosition = Point(0, 2)

        // act
        val direction = PowerOfThor().direction(thorPosition, lightPosition)

        // assert
        assertThat(direction).isEqualTo("SW")
    }

    @Test
    fun should_move_to_NORTH() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "N"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(1, 0))
    }

    @Test
    fun should_move_to_EAST() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "E"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(2, 1))
    }

    @Test
    fun should_move_to_SOUTH() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "S"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(1, 2))
    }

    @Test
    fun should_move_to_WEST() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "W"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(2, 1))
    }

    @Test
    fun should_move_to_NORTH_EAST() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "NE"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(2, 0))
    }

    @Test
    fun should_move_to_NORTH_WEST() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "NW"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(0, 0))
    }

    @Test
    fun should_move_to_SOUTH_EAST() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "SE"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(2, 2))
    }

    @Test
    fun should_move_to_SOUTH_WEST() {
        // arrange
        val thorPosition = Point(1, 1)
        val direction = "SW"

        // act
        val position = PowerOfThor().move(thorPosition, direction)

        // assert
        assertThat(position).isEqualTo(Point(0, 2))
    }

}
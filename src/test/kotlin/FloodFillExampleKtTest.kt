import FloodFillExample.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class FloodFillExampleKtTest {


    //    Example:
//
//    Consider the following grid:
//    ...#.
//    A#...
//    #..B.
//    .....
//
//    At time = 1, they will reach…
//    A..#.
//    A#.B.
//    #.BBB
//    ...B.
//
//    At time = 2, they will reach…
//    AA.#.
//    A#BBB
//    #BBBB
//    ..BBB
//
//    At time = 3, they will reach…
//    AA+#B
//    A#BBB
//    #BBBB
//    .BBBB
//    (Note that, both A and B can reach spot (2,0) at time = 3, so that spot is made into ‘+’)
//
//    At time = 4, they will reach…
//    AA+#B
//    A#BBB
//    #BBBB
//    BBBBB
//
//    This is what your program must output.
    @Test
    @Disabled
    fun should_flood_example_1() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...#.",
                "A#...",
                "#..B.",
                ".....",
            )
        )

        // act
        val flood = FloodFillExample().flood(defenceMap, 1)

        // assert
        assertThat(flood).hasSize(7)
    }

    @Test
    fun should_instantiate_defence_map() {
        // arrange

        // act
        val defenceMap = DefenceMap(
            listOf(
                "....",
                "A#..",
                "....",
            )
        )

        // assert
        assertThat(defenceMap.width).isEqualTo(4)
        assertThat(defenceMap.height).isEqualTo(3)
    }

    @Test
    fun should_find_tower_on_map() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                "A#.",
                "...",
            )
        )

        // act
        val towers = defenceMap.towers()

        // assert
        assertThat(towers).containsExactly(Tower('A', 0, 1))
    }

    @Test
    fun should_neighbors_of_top_left() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                "...",
                "...",
            )
        )

        // act
        val neighbors = Flood(0, 0, 'A', 0).neighbors(defenceMap)

        // assert
        assertThat(neighbors).containsExactly(
            Flood(0, 1, 'A', 1),
            Flood(1, 0, 'A', 1),
        )
    }

    @Test
    fun should_neighbors_of_top_right() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                "...",
                "...",
            )
        )

        // act
        val neighbors = Flood(2, 0, 'A', 1).neighbors(defenceMap)

        // assert
        assertThat(neighbors).containsExactly(
            Flood(2, 1, 'A', 2),
            Flood(1, 0, 'A', 2),
        )
    }

    @Test
    fun should_neighbors_of_bottom_left() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                "...",
                "...",
            )
        )

        // act
        val neighbors = Flood(0, 2, 'A', 2).neighbors(defenceMap)

        // assert
        assertThat(neighbors).containsExactly(
            Flood(0, 1, 'A', 3),
            Flood(1, 2, 'A', 3),
        )
    }

    @Test
    fun should_neighbors_of_bottom_right() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                "...",
                "...",
            )
        )

        // act
        val neighbors = Flood(2, 2, 'A', 4).neighbors(defenceMap)

        // assert
        assertThat(neighbors).containsExactly(
            Flood(2, 1, 'A', 5),
            Flood(1, 2, 'A', 5),
        )
    }

    @Test
    fun should_neighbors_center() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                "...",
                "...",
            )
        )

        // act
        val neighbors = Flood(1, 1, 'A', 5).neighbors(defenceMap)

        // assert
        assertThat(neighbors).containsExactly(
            Flood(1, 0, 'A', 6),
            Flood(1, 2, 'A', 6),
            Flood(0, 1, 'A', 6),
            Flood(2, 1, 'A', 6),
        )
    }

    @Test
    fun should_neighbors_with_unvisitable() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                "#..",
                ".#.",
            )
        )

        // act
        val neighbors = Flood(1, 1, 'A', 5).neighbors(defenceMap)

        // assert
        assertThat(neighbors).containsExactly(
            Flood(1, 0, 'A', 6),
            Flood(2, 1, 'A', 6),
        )
    }

    @Test
    fun should_return_char_on_defence_map() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "AB",
                "CD",
            )
        )

        // act
        val char = defenceMap.charAt(0, 0)

        // assert
        assertThat(char).isEqualTo('A')
    }

    @Test
    fun should_return_true_when_point_is_visible_on_defence_map() {
        // arrange
        val defenceMap = DefenceMap(listOf(".#"))

        // act
        val visitable = defenceMap.isVisitable(0, 0)

        // assert
        assertThat(visitable).isTrue()
    }
}
import org.assertj.core.api.Assertions
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
    fun should_flood_example_1() {
        // arrange
        val map = listOf(
            "...#.",
            "A#...",
            "#..B.",
            ".....",
        )

        // act
        val filledMap = FloodFillExample().fill(map, 1)

        // assert
        Assertions.assertThat(filledMap).isEqualTo(
            listOf(
                "A..#.",
                "A#.B.",
                "#.BBB",
                "...B.",
            )
        )
    }

}
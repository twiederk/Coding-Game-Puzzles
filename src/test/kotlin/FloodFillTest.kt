import FloodFill.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FloodFillTest {

    private val floodFill = FloodFill()

    @Test
    fun should_flood_test_1() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "..#.#...##",
                "#..#.#....",
                ".........#",
                "..#..#..#.",
                ".......#..",
                "..#.JEDI.#",
                "..#.....#.",
                ".....#..#.",
                "..........",
                "..........",
            )
        )

        // act
        val flood = floodFill.flood(defenceMap)

        // assert
        assertThat(floodFill.render(defenceMap, flood)).isEqualTo(
            """
                JJ#.#DDD##
                #JJ#J#DDDD
                JJJJJ+DDD#
                JJ#JJ#DD#I
                JJJJJED#II
                JJ#JJEDII#
                JJ#JJEDI#I
                JJJJJ#DI#I
                JJJJJ+DIII
                JJJJJ+DIII                
            """.trimIndent()
        )
    }

    @Test
    fun should_flood_test_2() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "#....##..#.#",
                "#.#..#..5..#",
                "...#........",
                "......##....",
                "......#..#..",
                "1...........",
                "............",
                ".#.2#.......",
                "...#..3.#.#.",
                "....#.....#.",
                "....#.....#.",
                "...4...#....",
            )
        )

        // act
        val flood = floodFill.flood(defenceMap)

        // assert
        assertThat(floodFill.render(defenceMap, flood)).isEqualTo(
            """
                #11+5##55#5#
                #1#55#55555#
                111#55555555
                111225##5555
                11122+#55#55
                11122+335555
                11222+333333
                1#22#3333333
                122#3333#3#3
                1++4#33333#3
                4444#+3333#3
                444444+#3333
            """.trimIndent()
        )
    }

    @Test
    fun should_flood_test_3() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "A.#.........#.#.#..L",
                "..#.........#..##...",
                "###..#.#.....###..#.",
                "#......#......#.....",
                "...D.....#..#.#.....",
                "...#..#....####.....",
                "...#...#......#..#..",
                "...#........####....",
                "..#..##......#...N..",
                ".E...#..#...........",
                "....#.#...#......###",
                "...#..#...#...#.#...",
                "..##......#.........",
                "....#.##...##...#.##",
                ".#...#...R..#.......",
                ".#..................",
                "...........#..#.....",
                ".......#......##....",
                "#...#.....#...##....",
                "...#................",
            )
        )

        // act
        val flood = floodFill.flood(defenceMap)

        // assert
        assertThat(floodFill.render(defenceMap, flood)).isEqualTo(
            """
                AA#DDDDDDDDD#.#.#LLL
                AA#DDDDDDDDD#..##LLL
                ###DD#D#DDDDD###LL#L
                #DDDDDD#DDDDDD#++LLL
                DDDDDDDDD#DD#D#NN++L
                DDD#DD#DDDD####NNNN+
                EED#DDD#D+++++#NN#NN
                EEE#DDDD+RR+####NNNN
                EE#EE##+RRR+N#NNNNNN
                EEEEE#RR#RR+NNNNNNNN
                EEEE#R#RRR#+NNNNN###
                EEE#RR#RRR#+NN#N#NNN
                EE##RRRRRR#+++NNNNNN
                EEEE#R##RRR##R+N#N##
                E#EER#RRRRRR#RR+++++
                E#ERRRRRRRRRRRRRRRRR
                EEERRRRRRRR#RR#RRRRR
                EEERRRR#RRRRRR##RRRR
                #EER#RRRRR#RRR##RRRR
                EEE#RRRRRRRRRRRRRRRR
            """.trimIndent()
        )
    }

    @Test
    fun should_flood_test_4() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "....#...........KYLO...#.",
                "#.....#...#..#........#.#",
                "..REY..........#..#......",
                "#...#..#.#........##.#.#.",
                "..#............#.........",
                "......#.........#......#.",
                ".#........#........#...#.",
                "#..HAN...................",
                "......#....#.##.....##.#.",
                "##..#....#.#....#..#.....",
                "..............###......#.",
                "#.....#.........#...##...",
                "...##..##.......#........",
                ".#.###..#.....#.#.....#.#",
                "....#...FN2187.....#.....",
                "..#.........#....#.#.....",
                "...........#...#.#...#...",
                ".#...##...##.....#..#..#.",
                "..................#...##.",
                ".....#.#....##.......#..#",
                "..........#........#.#...",
                ".#.............#......#.#",
                "...#.#.#.###..#..#.....##",
                "#.#........###.......#...",
                "..##.LEIA......#..#.##.##",
            )
        )

        // act
        val flood = floodFill.flood(defenceMap)

        // assert
        assertThat(floodFill.render(defenceMap, flood)).isEqualTo(
            """
                RRRE#YYYY+KKKKKKKYLOOOO#.
                #RREYY#YYY#KK#KKKYLOOO#O#
                RRREYYYYYYY+KKK#KY#OOOOOO
                #RRE#YY#Y#Y+KKKKKY##O#O#O
                RR#E++++++++KKK#KYY+OOOOO
                ++HHAN#NNNNN+KKK#YY+OOO#O
                +#HHANNNNN#N++KYYYY#OOO#O
                #HHHANNNNNNN++++YYY+OOOOO
                HHHHAN#NNN+#8##7+YY+##O#O
                ##HH#NNNN#2#8777#++#7+OOO
                HHHH+NNNNN2187###77777+#+
                #HHH+N#NNN218777#777##777
                HHH##+F##N218777#77777777
                H#H###FF#N2187#7#77777#7#
                +++F#FFFFN218777777#77777
                ++#FFFFFFN21#7777#7#77777
                FFFFFFFFFN2#777#7#777#777
                F#FFF##FFN##77777#77#77#7
                LLLLL++FFNN+777777#777##7
                LLLLL#E#+NN+##7777777#..#
                LLLLLEE+AA#AA777777#7#...
                L#LLLEE+AAAAAA7#777777#.#
                LLL#L#E#A###AA#AA#AAAAA##
                #L#LLLEIAAA###AAAAAAA#AAA
                LL##LLEIAAAAAAA#AA#A##A##
            """.trimIndent()
        )
    }

    @Test
    fun should_flood_test_5() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "A.A",
            )
        )

        // act
        val flood = FloodFill().flood(defenceMap)

        // assert
        println(FloodFill().render(defenceMap, flood))
        assertThat(flood).containsExactly(
            Flood(x = 0, y = 0, id = 'A', steps = 0),
            Flood(x = 2, y = 0, id = 'A', steps = 0),
            Flood(x = 1, y = 0, id = '+', steps = 1)
        )
    }

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
        val defenceMap = DefenceMap(
            listOf(
                "...#.",
                "A#...",
                "#..B.",
                ".....",
            )
        )

        // act
        val flood = FloodFill().flood(defenceMap)

        // assert
        assertThat(FloodFill().render(defenceMap, flood)).isEqualTo(
            """
            AA+#B
            A#BBB
            #BBBB
            BBBBB
        """.trimIndent()
        )
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

    @Test
    fun should_bfs_min_map_with_step_1() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                ".A.",
                "...",
            )
        )

        // act
        val flood = FloodFill().flood(defenceMap)

        // assert
        assertThat(FloodFill().render(defenceMap, flood, 1)).isEqualTo(
            """
            .A.
            AAA
            .A.
        """.trimIndent()

        )
    }

    @Test
    fun should_return_true_when_flood_with_this_coordinates_exists() {
        // act
        val result = FloodFill().contains(setOf(Flood(0, 0, 'B', 1)), Flood(0, 0, 'A', 0))

        // assert
        assertThat(result).isTrue()
    }

    @Test
    fun should_return_false_when_flood_with_this_coordinates_is_missing() {
        // act
        val result = FloodFill().contains(setOf(Flood(0, 1, 'B', 1)), Flood(0, 0, 'A', 0))

        // assert
        assertThat(result).isFalse()
    }

    @Test
    fun should_render_simple_map() {
        // arrange
        val defenceMap = DefenceMap(
            listOf(
                "...",
                ".A.",
                "...",
            )
        )
        val flood = FloodFill().flood(defenceMap)

        // act
        val output = FloodFill().render(defenceMap, flood)

        // assert
        assertThat(output).isEqualTo(
            """
            AAA
            AAA
            AAA
        """.trimIndent()
        )
    }
}
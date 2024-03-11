import org.assertj.core.api.Assertions.assertThat
//import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Test

class CodeVsZombiesTest {

    @Test
    fun should_create_vector_between_one_human_and_one_zombies() {
        // arrange
        val human = Human(0, Point2D(100, 200))
        val zombie = Zombie(0, Point2D(200, 300), Point2D(300, 400))

        // act
        val vector = human.vector(zombie)

        // assert
        assertThat(vector.length.toInt()).isEqualTo(282)
    }

    @Test
    fun should_create_vectors_between_one_human_and_list_of_zombies() {
        // arrange
        val human = Human(0, Point2D(100, 200))
        val zombies = listOf(
            Zombie(0, Point2D(200, 300), Point2D(300, 400)),
            Zombie(1, Point2D(2000, 3000), Point2D(3000, 4000)),
        )

        // act
        val vectors = human.vectors(zombies)

        // assert
        assertThat(vectors.map { it.length.toInt() }).contains(282, 4780)
    }

    @Test
    fun should_create_priority_queue_sorted_by_length_between_humans_and_zombies() {
        // arrange
        val humans = listOf(
            Human(0, Point2D(100, 200)),
            Human(1, Point2D(200, 300)),
        )
        val zombies = listOf(
            Zombie(0, Point2D(2000, 3000), Point2D(3000, 4000)),
            Zombie(1, Point2D(3000, 4000), Point2D(4000, 5000)),
        )

        // act
        val priorityQueue = CodeVsZombies().priorityQueue(Point2D(0, 0), humans, zombies)

        // assert
        assertThat(priorityQueue).hasSize(4)
        assertThat(priorityQueue.remove().length.toInt()).isEqualTo(4640)
        assertThat(priorityQueue.remove().length.toInt()).isEqualTo(4780)
        assertThat(priorityQueue.remove().length.toInt()).isEqualTo(6044)
        assertThat(priorityQueue.remove().length.toInt()).isEqualTo(6184)
    }

    @Test
    fun should_move_to_nearest_zombie() {
        // arrange
        val humans = listOf(
            Human(0, Point2D(100, 200)),
            Human(1, Point2D(200, 300)),
        )
        val zombies = listOf(
            Zombie(0, Point2D(2000, 3000), Point2D(3000, 4000)),
            Zombie(1, Point2D(3000, 4000), Point2D(4000, 5000)),
        )
        val ash = Point2D(100, 200)

        // act
        val move = CodeVsZombies().move(ash, humans, zombies)

        // assert
        assertThat(move).isEqualTo(Point2D(3000, 4000))
    }

    @Test
    fun should_return_true_when_ash_can_reach_human_before_zombie() {
        // arrange
        val vectorHZ = VectorHZ(
            Human(0, Point2D(1000, 1000)),
            Zombie(0, Point2D(1200, 1000), Point2D(800, 1000)),
        )

        // act
        val reachable = vectorHZ.isReachable(Point2D(2000, 1000))

        // assert
        assertThat(reachable).isTrue()
    }

    @Test
    fun should_return_false_when_ash_cannot_reach_human_before_zombie() {
        // arrange
        val vectorHZ = VectorHZ(
            Human(0, Point2D(1000, 1000)),
            Zombie(0, Point2D(800, 1000), Point2D(400, 1000)),
        )

        // act
        val reachable = vectorHZ.isReachable(Point2D(2500, 1000))

        // assert
        assertThat(reachable).isFalse()
    }

    @Test
    fun should_filter_out_all_humans_which_cannot_be_reached_by_ash_anymore() {
        // arrange
        val humans = listOf(
            Human(0, Point2D(1000, 1000)),
            Human(1, Point2D(7000, 1000)),
        )
        val zombies = listOf(
            Zombie(0, Point2D(200, 1000), Point2D(600, 1000)),
            Zombie(1, Point2D(8600, 1000), Point2D(8200, 1000)),
        )
        val ash = Point2D(4000, 1000)

        // act
        val filteredVectors = CodeVsZombies().priorityQueue(ash, humans, zombies)

        // assert
        assertThat(filteredVectors).hasSize(2)
    }

    @Test
    fun should_return_true_when_the_human_is_reachable() {
        // arrange
        val vector = VectorHZ(
            Human(humanId = 0, position = Point2D(x = 8250, y = 4500)),
            Zombie(zombieId = 0, position = Point2D(x = 8250, y = 8999), nextPosition = Point2D(x = 8250, y = 8599))
        )
        val ash = Point2D(x = 0, y = 0)


        // act
        val reachable = vector.isReachable(ash)

        // assert
        assertThat(reachable).isTrue()
    }

    @Test
    fun should_not_fail_with_NullPointerException() {
        // arrange
        val ash = Point2D(x = 0, y = 0)
        val humans = listOf(Human(humanId = 0, position = Point2D(x = 8250, y = 4500)))
        val zombies = listOf(
            Zombie(
                zombieId = 0,
                position = Point2D(x = 8250, y = 8999),
                nextPosition = Point2D(x = 8250, y = 8599)
            )
        )

        // act
        val move = CodeVsZombies().move(ash, humans, zombies)

        // assert
    }
}
import org.assertj.core.api.Assertions.assertThat
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
        assertThat(vector.length).isEqualTo(400)
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
        assertThat(vectors.map { it.length }).contains(400, 6700)
    }

    @Test
    fun should_create_priority_queue_sorted_by_length_between_humans_and_zombies() {
        // arrange
        val humans = listOf(
            Human(0, Point2D(100, 200)),
            Human(0, Point2D(200, 300)),
        )
        val zombies = listOf(
            Zombie(0, Point2D(2000, 3000), Point2D(3000, 4000)),
            Zombie(1, Point2D(3000, 4000), Point2D(4000, 5000)),
        )

        // act
        val priorityQueue = CodeVsZombies().priorityQueue(humans, zombies)

        // assert
        assertThat(priorityQueue).hasSize(4)
        assertThat(priorityQueue.remove().length).isEqualTo(6500)
        assertThat(priorityQueue.remove().length).isEqualTo(6700)
        assertThat(priorityQueue.remove().length).isEqualTo(8500)
        assertThat(priorityQueue.remove().length).isEqualTo(8700)
    }

    @Test
    fun should_move_to_nearest_zombie() {
        // arrange
        val humans = listOf(
            Human(0, Point2D(100, 200)),
            Human(0, Point2D(200, 300)),
        )
        val zombies = listOf(
            Zombie(0, Point2D(2000, 3000), Point2D(3000, 4000)),
            Zombie(1, Point2D(3000, 4000), Point2D(4000, 5000)),
        )

        // act
        val move = CodeVsZombies().move(Point2D(0, 0), humans, zombies)

        // assert
        assertThat(move).isEqualTo(Point2D(3000, 4000))

    }
}
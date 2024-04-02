import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BinarySearchTreeTraversalTest {

    @Test
    fun should_display_pre_order_for_test1() {
        // arrange
        val tree = BinarySearchTreeTraversal().buildTree(listOf(8, 6, 13, 10, 5))

        // act
        val result = tree.preOrder()

        // assert
        assertThat(result).isEqualTo("8 6 5 13 10")
    }
}
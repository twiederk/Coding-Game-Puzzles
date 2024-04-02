import BinarySearchTreeTraversal.Node
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BinarySearchTreeTraversalTest {

    private val test1 = listOf(8, 6, 13, 10, 5)

    @Test
    fun should_display_pre_order_for_test1() {
        // arrange
        val tree = BinarySearchTreeTraversal().buildTree(listOf(8, 6, 13, 10, 5))

        // act
        val result = tree.preOrder()

        // assert
        assertThat(result).isEqualTo("8 6 5 13 10")
    }

    @Test
    fun should_build_tree_with_root_node() {

        // act
        val tree = BinarySearchTreeTraversal().buildTree(listOf(8))

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
    }

    @Test
    fun should_build_tree_with_root_and_left_child() {

        // act
        val tree = BinarySearchTreeTraversal().buildTree(listOf(8, 6))

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
        assertThat(tree.root.left).isEqualTo(Node(6))
        assertThat(tree.root.right).isNull()
    }

    @Test
    fun should_build_tree_with_root_and_right_child() {
        // arrange

        // act
        val tree = BinarySearchTreeTraversal().buildTree(listOf(8, 10))

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
        assertThat(tree.root.right).isEqualTo(Node(10))
        assertThat(tree.root.left).isNull()
    }

    @Test
    fun should_built_tree_of_test_1() {

        // act
        val tree = BinarySearchTreeTraversal().buildTree(test1)

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
        assertThat(tree.root.left).isEqualTo(Node(6))
        assertThat(tree.root.left?.left).isEqualTo(Node(5))
        assertThat(tree.root.right).isEqualTo(Node(13))
        assertThat(tree.root.right?.left).isEqualTo(Node(10))
    }
}
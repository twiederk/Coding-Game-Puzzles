import BinarySearchTreeTraversal.Node
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BinarySearchTreeTraversalTest {

    private val test1 = listOf(8, 6, 13, 10, 5)

    @Test
    fun should_display_pre_order_for_test1() {
        // arrange
        val tree = BinarySearchTreeTraversal(listOf(8, 6, 13, 10, 5)).buildTree()

        // act
        val result = tree.preOrder()

        // assert
        assertThat(result).isEqualTo("8 6 5 13 10")
    }

    @Test
    fun should_display_in_order_for_test1() {
        // arrange
        val tree = BinarySearchTreeTraversal(listOf(8, 6, 13, 10, 5)).buildTree()

        // act
        val result = tree.inOrder()

        // assert
        assertThat(result).isEqualTo("5 6 8 10 13")
    }

    @Test
    fun should_display_post_order_for_test1() {
        // arrange
        val tree = BinarySearchTreeTraversal(listOf(8, 6, 13, 10, 5)).buildTree()

        // act
        val result = tree.postOrder()

        // assert
        assertThat(result).isEqualTo("5 6 10 13 8")
    }

    @Test
    fun should_display_level_order_for_test1() {
        // arrange
        val tree = BinarySearchTreeTraversal(listOf(8, 6, 13, 10, 5)).buildTree()

        // act
        val result = tree.levelOrder()

        // assert
        assertThat(result).isEqualTo("8 6 13 5 10")
    }

    @Test
    fun should_build_tree_with_root_node() {

        // act
        val tree = BinarySearchTreeTraversal(listOf(8)).buildTree()

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
    }

    @Test
    fun should_build_tree_with_root_and_left_child() {

        // act
        val tree = BinarySearchTreeTraversal(listOf(8, 6)).buildTree()

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
        assertThat(tree.root.left).isEqualTo(Node(6))
        assertThat(tree.root.right).isNull()
    }

    @Test
    fun should_build_tree_with_root_and_right_child() {
        // arrange

        // act
        val tree = BinarySearchTreeTraversal(listOf(8, 10)).buildTree()

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
        assertThat(tree.root.right).isEqualTo(Node(10))
        assertThat(tree.root.left).isNull()
    }

    @Test
    fun should_built_tree_of_test_1() {

        // act
        val tree = BinarySearchTreeTraversal(test1).buildTree()

        // assert
        assertThat(tree.root).isEqualTo(Node(8))
        assertThat(tree.root.left).isEqualTo(Node(6))
        assertThat(tree.root.left?.left).isEqualTo(Node(5))
        assertThat(tree.root.right).isEqualTo(Node(13))
        assertThat(tree.root.right?.left).isEqualTo(Node(10))
    }
}
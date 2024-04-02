import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    val inputNumbers = mutableListOf<Int>()
    for (i in 0 until n) {
        inputNumbers.add(input.nextInt())
    }

    val tree = BinarySearchTreeTraversal().buildTree(inputNumbers)

    println(tree.preOrder())
    println(tree.inOrder())
    println(tree.postOrder())
    println(tree.levelOrder())
}

class BinarySearchTreeTraversal {
    fun buildTree(inputNumbers: List<Int>): Tree {
        return Tree()
    }

    class Tree {
        fun preOrder(): String {
            return ""
        }

        fun inOrder(): String {
            return ""
        }

        fun postOrder(): String {
            return ""
        }

        fun levelOrder(): String {
            return ""
        }
    }

    class Node(
        var key: Int,
        var left: Node? = null,
        var right: Node? = null
    ) {
    }

}
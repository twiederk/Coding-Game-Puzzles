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
        val tree = Tree(Node(inputNumbers[0]))
        for (number in inputNumbers.subList(1, inputNumbers.size)) {
            tree.add(Node(number))
        }
        return tree
    }

    class Tree(
        val root: Node
    ) {

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

        fun add(node: Node) {
            var current = root
            while (true) {
                if (node.key < current.key) {
                    if (current.left == null) {
                        current.left = node
                        return
                    } else {
                        current = current.left!!
                    }
                } else {
                    if (current.right == null) {
                        current.right = node
                        return
                    } else {
                        current = current.right!!
                    }
                }
            }
        }
    }

    data class Node(
        val key: Int
    ) {
        var left: Node? = null
        var right: Node? = null
    }

}
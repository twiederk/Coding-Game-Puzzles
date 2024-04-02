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

    val tree = BinarySearchTreeTraversal(inputNumbers).buildTree()

    println(tree.preOrder())
    println(tree.inOrder())
    println(tree.postOrder())
    println(tree.levelOrder())
}

class BinarySearchTreeTraversal(
    private val inputNumbers: List<Int>
) {
    fun buildTree(): Tree {
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
            val output = mutableListOf<Int>()
            return root.preOrder(output)
        }

        fun inOrder(): String {
            val output = mutableListOf<Int>()
            return root.inOrder(output)
        }

        fun postOrder(): String {
            val output = mutableListOf<Int>()
            return root.postOrder(output)
        }

        fun levelOrder(): String {
            return bfs().joinToString(" ")
        }

        private fun bfs(): List<Int> {
            val queue = LinkedList<Node>()
            val output = mutableListOf<Int>()
            queue.add(root)
            while (queue.isNotEmpty()) {
                val current = queue.poll()
                output.add(current.key)
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
            return output

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
        fun preOrder(output: MutableList<Int>): String {
            output.add(key)
            left?.preOrder(output)
            right?.preOrder(output)
            return output.joinToString(" ")
        }

        fun inOrder(output: MutableList<Int>): String {
            left?.inOrder(output)
            output.add(key)
            right?.inOrder(output)
            return output.joinToString(" ")
        }

        fun postOrder(output: MutableList<Int>): String {
            left?.postOrder(output)
            right?.postOrder(output)
            output.add(key)
            return output.joinToString(" ")
        }

        var left: Node? = null
        var right: Node? = null
    }

}
package org.example


private val graph = mapOf(
    "a" to listOf("b", "c"),
    "b" to listOf("d"),
    "c" to listOf("e"),
    "d" to listOf("f"),
    "e" to listOf(),
    "f" to listOf(),

)
fun depthFirstPrint(graph: Map<String, List<String>>, source: String) {
    val stack = mutableListOf(source)

    while (stack.isNotEmpty()) {
        val current = stack.removeLast()
        println(current)
        for (neighbour in graph[current]!!) {
            stack.add(neighbour)
        }
    }
}

fun depthFirstPrintRecursive(graph: Map<String, List<String>>, source: String) {
    println(source)

    // Implicit base case is where source has no neighbours
    for (neighbour in graph[source]!!) {
        depthFirstPrintRecursive(graph, neighbour)
    }
}

fun breadthFirstPrint(graph: Map<String, List<String>>, source: String) {
    val queue = mutableListOf(source)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        println(current)

        for (neighbour in graph[current]!!) {
            queue.add(neighbour)
        }
    }
}

fun main() {
    depthFirstPrint(graph, "a")  // acebdf
    println()
    depthFirstPrintRecursive(graph, "a")  // abdfce
    println()
    breadthFirstPrint(graph, "a")  // abcdef
}
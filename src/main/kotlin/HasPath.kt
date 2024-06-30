package org.example

private val graph = mapOf(
    "f" to listOf("g", "i"),
    "g" to listOf("h"),
    "h" to listOf(),
    "i" to listOf("g", "k"),
    "j" to listOf("i"),
    "k" to listOf()

)

fun hasPath(graph: Map<String, List<String>>, src: String, dest: String): Boolean {
    if (src == dest) return true

    val neighbours = graph[src]!!

    for (neighbour in neighbours) {
        if (hasPath(graph, neighbour, dest)) return true
    }

    return false
}

fun hasPathBreadthFirst(graph: Map<String, List<String>>, src: String, dest: String): Boolean {
    val queue = ArrayDeque<String>()
    queue.add(src)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        if (current == dest) return true

        for (neighbour in graph[current]!!) {
            queue.add(neighbour)
        }
    }

    return false
}

fun main() {
    println(hasPath(graph, "f", "k"))  // true
    println(hasPath(graph, "i", "f"))  // false

    println(hasPathBreadthFirst(graph, "f", "k"))  // true
    println(hasPathBreadthFirst(graph, "i", "f"))  // false
}
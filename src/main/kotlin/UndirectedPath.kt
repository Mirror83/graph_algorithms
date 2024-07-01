package org.example


private val edgeList = listOf(
    listOf("i", "j"),
    listOf("k", "j"),
    listOf("m", "k"),
    listOf("k", "l"),
    listOf("o", "n"),
)

fun <T> List<List<T>>.toAdjacencyList(): Map<T, List<T>> {
    val graph = mutableMapOf<T, MutableList<T>>()
    for (edge in this) {
        val (a, b) = edge
        if (!graph.containsKey(a)) graph[a] = mutableListOf()
        if (!graph.containsKey(b)) graph[b] = mutableListOf()

        graph[a]!!.add(b)
        graph[b]!!.add(a)
    }

    return graph
}

fun undirectedPath(edgeList: List<List<String>>, src: String, dest: String): Boolean {
    val graph = edgeList.toAdjacencyList()
    return hasUndirectedPath(graph, src, dest)

}

fun undirectedPathBreadthFirst(edgeList: List<List<String>>, src: String, dest: String): Boolean {
    val graph = edgeList.toAdjacencyList()
    return hasUndirectedPathBreadthFirst(graph, src, dest)
}

private fun hasUndirectedPath(
    graph: Map<String, List<String>>,
    src: String,
    dest: String,
    visitedSet: MutableSet<String> = mutableSetOf()
): Boolean {
    if (src == dest) return true
    if (visitedSet.contains(src)) return false

    visitedSet.add(src)

    for (neighbour in graph[src]!!) {
        if (hasUndirectedPath(graph, neighbour, dest, visitedSet)) return true
    }

    return false
}

private fun hasUndirectedPathBreadthFirst(
    graph: Map<String, List<String>>,
    src: String,
    dest: String,
): Boolean {
    val queue = ArrayDeque<String>()
    val visitedSet = mutableSetOf<String>()
    queue.add(src)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        if (visitedSet.contains(current)) continue

        visitedSet.add(current)
        if (current == dest) return true

        val neighbours = graph[current]!!

        for (neighbour in neighbours) {
            queue.add(neighbour)
        }
    }

    return false
}

fun main() {
    println(undirectedPath(edgeList, "j", "m"))  // true
    println(undirectedPath(edgeList, "k", "o"))  // false

    println(undirectedPathBreadthFirst(edgeList, "j", "m"))  // true
    println(undirectedPathBreadthFirst(edgeList, "k", "o"))  // false
}
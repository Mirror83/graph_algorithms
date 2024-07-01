package org.example

private val edgeList = listOf(
    listOf("w", "x"),
    listOf("x", "y"),
    listOf("z", "y"),
    listOf("z", "v"),
    listOf("w", "v"),
)

private val edgeList2 = listOf(
    listOf("a", "c"),
    listOf("a", "b"),
    listOf("c", "b"),
    listOf("c", "d"),
    listOf("b", "d"),
    listOf("e", "d"),
    listOf("g", "f"),
)

fun <T> shortestPathLength(edgeList: List<List<T>>, source: T, dest: T): Int {
    val graph = edgeList.toAdjacencyList()
    val visitedSet = mutableSetOf<T>()

    val queue = ArrayDeque<Pair<T, Int>>()

    queue.add(source to 0)

    while (queue.isNotEmpty()) {
        val (current, distance) = queue.removeFirst()
        if (current == dest) return distance

        if (visitedSet.contains(current)) continue
        visitedSet.add(current)

        for (neighbour in graph[current]!!) {
            queue.add(neighbour to (distance + 1))
        }
    }

    return -1
}

fun main() {
    println(shortestPathLength(edgeList, "w", "z"))  // 2
    println(shortestPathLength(edgeList2, "b", "g"))  // -1
}
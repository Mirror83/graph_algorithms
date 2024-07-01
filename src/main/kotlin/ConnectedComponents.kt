package org.example

private val graph = mapOf(
    3 to listOf(),
    4 to listOf(6),
    6 to listOf(4, 5, 7, 8),
    8 to listOf(6),
    7 to listOf(6),
    5 to listOf(6),
    1 to listOf(2),
    2 to listOf(1),
)

private val graph2 = mapOf(
    0 to listOf(8, 1, 5),
    1 to listOf(0),
    5 to listOf(0, 8),
    8 to listOf(0, 5),
    2 to listOf(3, 4),
    3 to listOf(2, 4),
    4 to listOf(3, 2),
)


fun <T> connectedComponentsCount(graph: Map<T, List<T>>): Int {
    val visitedSet = mutableSetOf<T>()
    var count = 0

    for (node in graph.keys) {
        // Breadth-first traversal for current node
        val isNodeFullyExplored = explore(graph, node, visitedSet)

        if (isNodeFullyExplored) count += 1
    }

    return count
}

fun <T> largestComponentSize(graph: Map<T, List<T>>): Int {
    val visitedSet = mutableSetOf<T>()

    var largest = 0

    for (node in graph.keys) {
        // Perform a breadth-first traversal
        val nodeComponentCount = exploreAndCount(graph, node, visitedSet)

        if (nodeComponentCount > largest) {
            largest = nodeComponentCount
        }

    }

    return largest
}

private fun <T> explore(graph: Map<T, List<T>>, node: T, visitedSet: MutableSet<T>): Boolean {
    if (visitedSet.contains(node))
        return false

    val queue = ArrayDeque<T>()

    queue.add(node)
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()

        if (visitedSet.contains(current))
            continue

        visitedSet.add(current)

        for (neighbour in graph[current]!!) {
            queue.add(neighbour)
        }
    }

    return true
}

private fun <T> exploreAndCount(graph: Map<T, List<T>>, node: T, visitedSet: MutableSet<T>): Int {
    if (visitedSet.contains(node))
        return 0

    var count = 0

    val queue = ArrayDeque<T>()
    queue.add(node)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()

        if (visitedSet.contains(current))
            continue

        visitedSet.add(current)
        count += 1

        for (neighbour in graph[current]!!) {
            queue.add(neighbour)
        }
    }

    return count
}

fun main() {
    println(connectedComponentsCount(graph))  // 3
    println(connectedComponentsCount(graph2))  // 2

    println(largestComponentSize(graph2))  // 4
}
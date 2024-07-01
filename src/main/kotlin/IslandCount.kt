package org.example

private val grid = listOf(
    listOf("W", "L", "W", "W", "W"),
    listOf("W", "L", "W", "W", "W"),
    listOf("W", "W", "W", "L", "W"),
    listOf("W", "W", "L", "L", "W"),
    listOf("L", "W", "W", "L", "L"),
    listOf("L", "L", "W", "W", "W"),
)

private val grid2 = listOf(
    listOf("W", "L", "W", "W", "L", "W"),
    listOf("L", "L", "W", "W", "L", "W"),
    listOf("W", "L", "W", "W", "W", "W"),
    listOf("W", "W", "W", "L", "L", "W"),
    listOf("W", "L", "W", "L", "L", "W"),
    listOf("W", "W", "W", "W", "W", "W"),
)

private val grid3 = listOf(
    listOf("W", "W"),
    listOf("W", "W"),
)

private val grid4 = listOf(
    listOf("L", "L", "L"),
    listOf("L", "L", "L"),
    listOf("L", "L", "L"),
)

fun islandCount(grid: List<List<String>>): Int {
    var islands = 0
    val visitedSet = mutableSetOf<Pair<Int, Int>>()

    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] != "L") continue

            val current = Pair(i, j)
            if (exploreLand(grid, current, visitedSet))
                islands += 1
        }
    }

    return islands
}

private fun exploreLand(grid: List<List<String>>, position: Pair<Int, Int>, visitedSet: MutableSet<Pair<Int, Int>>): Boolean {
    if (visitedSet.contains(position)) return false

    visitedSet.add(position)

    val neighbours = getNeighbours(position, grid.size, grid[0].size)
    for (neighbour in neighbours) {
        if (grid[neighbour.first][neighbour.second] == "L") {
            exploreLand(grid, neighbour, visitedSet)
        }
    }

    return true
}

private fun getNeighbours(position: Pair<Int, Int>, rows: Int, cols: Int): List<Pair<Int, Int>> {
    val left = Pair(position.first, position.second - 1)
    val right = Pair(position.first, position.second + 1)

    val up = Pair(position.first - 1, position.second)
    val down = Pair(position.first + 1, position.second)

    val neighbours = mutableListOf<Pair<Int, Int>>()
    if (left.second >= 0) neighbours.add(left)
    if (right.second < cols) neighbours.add(right)

    if (up.first > 0) neighbours.add(up)
    if (down.first < rows) neighbours.add(down)

    return neighbours
}

private fun getLandSize(grid: List<List<String>>, position: Pair<Int, Int>, visitedSet: MutableSet<Pair<Int, Int>>): Int {
    if (visitedSet.contains(position)) return 0

    var size = 1
    visitedSet.add(position)
    val neighbours = getNeighbours(position, grid.size, grid[0].size)

    for (neighbour in neighbours) {
        if (grid[neighbour.first][neighbour.second] == "L")
            size += getLandSize(grid, neighbour, visitedSet)
    }

    return size
}

fun minIslandSize(grid: List<List<String>>): Int {
    var minSize = Int.MAX_VALUE
    val visitedSet = mutableSetOf<Pair<Int, Int>>()

    for (i in grid.indices) {
        for (j in grid[0].indices) {
            if (grid[i][j] != "L") continue
            val current = Pair(i, j)
            val landSizeFromCurrent = getLandSize(grid, current, visitedSet)
            if (landSizeFromCurrent in 1..<minSize)
                minSize = landSizeFromCurrent
        }
    }

    return if (minSize == Int.MAX_VALUE) 0 else minSize

}

fun main() {
    println("Island Count")
    println(islandCount(grid))  // 3
    println(islandCount(grid2))  // 4
    println(islandCount(grid3))  // 0
    println(islandCount(grid4))  // 1

    println("\nMin Island Size")
    println(minIslandSize(grid))  // 2
    println(minIslandSize(grid2))  // 1
    println(minIslandSize(grid3))  // 0
    println(minIslandSize(grid4))  // 9
}
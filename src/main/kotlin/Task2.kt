/**
 * Task 2
 * You are given a list of cities. Each direct connection between two cities has its
 * transportation cost (an integer bigger than 0). The goal is to find the paths of minimum cost
 * between pairs of cities. Assume that the cost of each path (which is the sum of costs of all
 * direct connections belonging to this path) is at most 200000.
 * The name of a city is a string containing characters a,...,z and is at most 10 characters long.2)
 *
 * Input
 * s [the number of tests <= 10]
 * n [the number of cities <= 10000]
 * NAME [city name]
 * p [the number of neighbors of city NAME]
 * nr cost [nr - index of a city connected to NAME (the index of the first city is 1)]
 *            [cost - the transportation cost]
 * r [the number of paths to find <= 100]
 * NAME1 NAME2 [NAME1 - source, NAME2 - destination]
 * [empty line separating the tests]
 *
 * Output
 *
 * cost [the minimum transportation cost from city NAME1 to city NAME2 (one per line)]
 *
 * Example
 *
 * Input:
 * 1
 * 4
 * gdansk
 * 2
 * 2 1
 * 3 3
 * bydgoszcz
 * 3
 * 1 1
 * 3 1
 * 4 4
 * torun
 * 3
 * 1 3
 * 2 1
 * 4 1
 * warszawa
 * 2
 * 2 4
 * 3 1
 * 2
 * gdansk warszawa
 * bydgoszcz warszawa
 *
 * Output:
 * 3
 * 2
 *
 *
 */
import java.util.*

class Task2 : BaseSolution() {

    fun calculateCostDijkstra2(
        graph: List<MutableList<Pair<Int, Int>>>,
        start: Int,
        end: Int
    ): Int {
        val distances = IntArray(graph.size) { Int.MAX_VALUE }
        distances[start] = 0

        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (current, cost) = queue.poll()
            if (cost > distances[current]) continue

            for ((neighbor, edgeCost) in graph[current]) {
                val newCost = cost + edgeCost
                if (newCost < distances[neighbor]) {
                    distances[neighbor] = newCost
                    queue.add(neighbor to newCost)
                }
            }
        }
        return distances[end]
    }

    override fun runSolution() {
        val scanner = Scanner(System.`in`)

        try {
            println("Enter the number of test cases:")
            val testCases = scanner.nextInt()

            repeat(testCases) {
                println("Enter the number of cities:")
                val cityCount = scanner.nextInt()
                val cityIndexMap = mutableMapOf<String, Int>()
                val graph = List(cityCount) { mutableListOf<Pair<Int, Int>>() }

                repeat(cityCount) { cityIndex ->
                    println("Enter city name:")
                    val cityName = scanner.next()
                    cityIndexMap[cityName] = cityIndex

                    println("Create connections for city ${cityName}.")
                    println("Enter the number of neighbors for city:")
                    repeat(scanner.nextInt()) {
                        println("Enter neighbor index and cost (e.g., '1 5'):")
                        val neighborIndex = scanner.nextInt() - 1
                        val cost = scanner.nextInt()

                        require(neighborIndex in graph.indices) { "Neighbor index out of bounds." }
                        graph[cityIndex].add(neighborIndex to cost)
                    }
                }

                println("Enter the number of paths to calculate:")
                repeat(scanner.nextInt()) {
                    println("Enter start and end city names (e.g., 'city1 city2'):")
                    val startName = scanner.next()
                    val endName = scanner.next()

                    val startIndex = cityIndexMap[startName]
                        ?: throw IllegalArgumentException("City $startName not found.")
                    val endIndex = cityIndexMap[endName]
                        ?: throw IllegalArgumentException("City $endName not found.")

                    val result = calculateCostDijkstra2(graph, startIndex, endIndex)
                    println(if (result == Int.MAX_VALUE) "NO PATH" else result)
                }
            }
        } catch (e: Exception) {
            println("Error: ${e.toString()}: ${e.message}")
        }
    }
}
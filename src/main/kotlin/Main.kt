fun main() {
    listOf<BaseSolution>(
        Task1(),
        Task2(),
        Task3()
    ).forEach { it.run() }
}

abstract class BaseSolution {
    val solutionName = this::class.simpleName

    protected abstract fun runSolution()

    fun run() {
        println("--------------------------")
        println("Running solution: $solutionName")
        runSolution()
    }
}

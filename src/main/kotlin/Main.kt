fun main() {
    listOf<BaseSolution>(
        Task1(),
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

import java.math.BigInteger

/**
 * Task 3. Find the sum of the digits in the number 100! (i.e. 100 factorial)
 * {Correct answer: 648}
 */
class Task3() : BaseSolution() {

    private fun factorial(n: Int): BigInteger = when {
        n < 0 -> throw IllegalArgumentException("Negative number")
        n == 0 -> BigInteger.ONE
        else -> n.toBigInteger() * factorial(n - 1)
    }

    private fun calculateDigits(inputNumber: Int) =
        factorial(inputNumber)
            .toString()
            .sumOf { it.toString().toInt() }


    override fun runSolution() {
        println("Enter the factorial number: ")
        val inputNumber = readLine()!!.toInt()

        println("The sum of the digits of $inputNumber! is ${calculateDigits(inputNumber)}")
    }

}
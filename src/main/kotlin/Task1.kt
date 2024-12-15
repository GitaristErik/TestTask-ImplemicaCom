/**
 * Задача 1
 * Если мы из корректно записанного арифметического выражения, содержащего числа,
 * знаки операций и открывающие и закрывающие круглые скобки выбросим числа и знаки операций,
 * а затем запишем оставшиеся в выражении скобки без пробелов между ними,
 * то полученный результат назовем правильным скобочным выражением
 *
 * [скобочное выражение "(()(()))" - правильное, а "()(" и "())(" - нет].
 *
 * Найти число правильных скобочных выражений, содержащих N открывающихся и N закрывающихся скобок.
 * N вводится с клавиатуры.
 * N неотрицательное целое число.
 * Пример:
 * N =  1 (по одной скобке открывающейся и закрывающейся) - ответ 1
 * ()
 * )(
 * ))
 * ((
 * Только один правильный вариант
 *
 * Для введенного числа 2 - 2 :
 * ()()
 * (())
 * То есть только два варианта, когда все открытые скобки правильно открываются/закрываются.
 * И так далее.
 */

class Task1 : BaseSolution() {

    private fun generateBrackets(n: Int): List<String> {
        val result = mutableListOf<String>()

        fun backtrack(current: String, open: Int, close: Int) {
            if (current.length == n * 2) {
                result.add(current)
                return
            }

            if (open < n) {
                backtrack("$current(", open + 1, close)
            }
            if (close < open) {
                backtrack("$current)", open, close + 1)
            }
        }

        backtrack("", 0, 0)
        return result
    }


    override fun runSolution() {
        println("Enter the number of brackets: ")
        val inputNumber = readLine()!!.toInt()

        val result = generateBrackets(inputNumber)
        println("Number of correct brackets: ${result.size}")
        println("Brackets: $result")
    }
}
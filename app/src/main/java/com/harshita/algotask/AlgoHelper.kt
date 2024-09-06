package com.harshita.algotask

import kotlin.math.sqrt

object AlgoHelper {
    fun getFibonacciNumbersInRange(start: Int, end: Int): List<Int> {
        val fibonacciNumbers = mutableListOf<Int>()
        var a = 0
        var b = 1

        while (b <= end) {
            if (b >= start) {
                fibonacciNumbers.add(b)
            }
            val temp = a + b
            a = b
            b = temp
        }

        return fibonacciNumbers
    }

    fun getOddNumber() {

    }

    private fun isPerfectSquare(num: Int): Boolean {
        val sqrtVal = sqrt(num.toDouble()).toInt()
        return (sqrtVal * sqrtVal == num)
    }

    // Function to check if a number is a Fibonacci number
    fun isFibonacciNumber(n: Int): Boolean {
        return isPerfectSquare(5 * n * n + 4) || isPerfectSquare(5 * n * n - 4)
    }
}
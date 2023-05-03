package com.practicum.jointproject

class Calculator {
    fun execute(strategy: CalculatorStrategy, num1: Int, num2: Int): Int{
        return strategy.execute(num1, num2)
    }
}
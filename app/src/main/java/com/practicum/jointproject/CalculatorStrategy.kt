package com.practicum.jointproject

interface CalculatorStrategy {
    fun execute(num1: Int, num2: Int): Int
}
class Multiplication: CalculatorStrategy{
    override fun execute(num1: Int, num2: Int): Int {
        return num1 * num2
    }

}
class Addition : CalculatorStrategy{
    override fun execute(num1: Int, num2: Int): Int {
        return num1 + num2
    }

}
class Subtract : CalculatorStrategy{
    override fun execute(num1: Int, num2: Int): Int {
     return num1 - num2
    }

}
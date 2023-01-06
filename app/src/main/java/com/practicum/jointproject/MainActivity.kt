package com.practicum.jointproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.practicum.jointproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var symbol = ""
    private var firstNum = 0.0
    private var secondNum = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.zero.setOnClickListener { addNumber(binding.zero) }
        binding.one.setOnClickListener { addNumber(binding.one) }
        binding.two.setOnClickListener { addNumber(binding.two) }
        binding.three.setOnClickListener { addNumber(binding.three) }
        binding.four.setOnClickListener { addNumber(binding.four) }
        binding.five.setOnClickListener { addNumber(binding.five) }
        binding.six.setOnClickListener { addNumber(binding.six) }
        binding.seven.setOnClickListener { addNumber(binding.seven) }
        binding.eight.setOnClickListener { addNumber(binding.eight) }
        binding.nine.setOnClickListener { addNumber(binding.nine) }
        binding.point.setOnClickListener { addNumber(binding.point) }

        binding.delete.setOnClickListener { clearing() }
        binding.backspace.setOnClickListener { binding.tvInput.text = binding.tvInput.text.dropLast(1) }

        binding.div.setOnClickListener { addSymbol(binding.div) }
        binding.mul.setOnClickListener { addSymbol(binding.mul) }
        binding.plus.setOnClickListener { addSymbol(binding.plus) }
        binding.minus.setOnClickListener { addSymbol(binding.minus) }
        binding.percent.setOnClickListener { addSymbol(binding.percent) }

        binding.equal.setOnClickListener {
            val list = binding.tvInput.text.split(Regex("[-+*/%]"))
            if (list.size == 2 && isDouble(list)) { //расчет первого выражения
                switchingSymbol()
                outputting()
            }
            else if (list.size == 2 && binding.tvResult.text.isNotEmpty()) { //работа с результатом
                secondNum = list[1].toDouble()
                switchingSymbol()
                outputting()
            }
            else if (list.size == 3 && list[1].isNotEmpty() && isNegative(list)) { //расчет первого выражения с отрицательным числом
                switchingSymbol()
                outputting()
            }
            else if (binding.tvResult.text.isNotEmpty())
                binding.tvInput.text = ""
            else clearing() //обработка неправильного ввода
        }
    }

    private fun addSymbol(btn: Button) {
        symbol = btn.text.toString()
        binding.tvInput.append(symbol)
    }

    private fun addNumber(btn: Button) {
        when (binding.tvInput.text.length) {
            in 0..15 -> binding.tvInput.append(btn.text)
            else -> binding.tvInput.text = getString(R.string.overload)
        }
    }

    private fun clearing() {
        firstNum = 0.0
        secondNum = 0.0
        binding.tvInput.text = ""
        binding.tvResult.text = ""
    }

    private fun isDouble(list: List<String>): Boolean {
        return try {
            firstNum = list[0].toDouble()
            secondNum = list[1].toDouble()
            true
        } catch (e: NumberFormatException) { false }
    }

    private fun isNegative(list: List<String>): Boolean {
        return try {
            if (binding.tvInput.text.take(1).toString() == "-") {
                firstNum = -1 * list[1].toDouble()
                secondNum = list[2].toDouble()
            }
            true
        } catch (e: NumberFormatException) { false }
    }

    private fun switchingSymbol() {
        when (symbol) {
            "+" -> { firstNum = firstNum.plus(secondNum) }
            "-" -> { firstNum = firstNum.minus(secondNum) }
            "*" -> { firstNum = firstNum.times(secondNum) }
            "/" -> { firstNum = firstNum.div(secondNum) }
            "%" -> { firstNum = firstNum.times(secondNum).div(100) }
        }
        binding.tvInput.text = ""
    }

    private fun outputting() {
        if (firstNum % 1 == 0.0)
            binding.tvResult.text = firstNum.toInt().toString()
        else
            binding.tvResult.text = firstNum.toFloat().toString()
    }
}


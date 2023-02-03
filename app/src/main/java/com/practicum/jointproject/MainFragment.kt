package com.practicum.jointproject

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import com.practicum.jointproject.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var anim: TransitionDrawable
    private var symbol = ""
    private var firstNum = 0.0
    private var secondNum = 0.0

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

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

        binding.delete.setOnClickListener {
            scaleButton(binding.delete)
            clearing() }
        binding.backspace.setOnClickListener {
            scaleButton(binding.backspace)
            binding.backspace.startAnimation(AnimationUtils.loadAnimation(context,R.anim.scale))
            binding.tvInput.text = binding.tvInput.text.dropLast(1)
        }

        binding.div.setOnClickListener { addSymbol(binding.div) }
        binding.mul.setOnClickListener { addSymbol(binding.mul) }
        binding.plus.setOnClickListener { addSymbol(binding.plus) }
        binding.minus.setOnClickListener { addSymbol(binding.minus) }
        binding.percent.setOnClickListener { addSymbol(binding.percent) }

        binding.equal.setOnClickListener {
            scaleButton(binding.equal)
            binding.equal.startAnimation(AnimationUtils.loadAnimation(context,R.anim.scale))
            val list = binding.tvInput.text.split(Regex("[-+*/%]"))
            when {
                (list.size == 2 && isDouble(list)) or (list.size == 3 && list[1].isNotEmpty() && isNegative(
                    list
                )) -> { //расчет первого выражения ( c положительным и отрицательным числом)
                    switchingSymbol()
                    outputting()
                }
                list.size == 2 && binding.tvResult.text.isNotEmpty() -> { //работа с результатом
                    secondNum = list[1].toDouble()
                    switchingSymbol()
                    outputting()
                }
                binding.tvResult.text.isNotEmpty() -> {
                    binding.tvInput.text = ""
                }
                list.size == 1 && list[0] == "0" -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                list.size == 1 && list[0] == "1" -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                else -> clearing() //обработка неправильного ввода
            }
        }
        return view
    }

    private fun addSymbol(btn: Button) {
        scaleButton(btn)
        symbol = btn.text.toString()
        binding.tvInput.append(symbol)
    }

    private fun addNumber(btn: Button) {
        scaleButton(btn)
        when (binding.tvInput.text.length) {
            in 0..14 -> binding.tvInput.append(btn.text)
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
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun isNegative(list: List<String>): Boolean {
        return try {
            if (binding.tvInput.text.take(1).toString() == "-") {
                firstNum = -1 * list[1].toDouble()
                secondNum = list[2].toDouble()
            }
            true
        } catch (e: NumberFormatException) {
            false
        }
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
        if (firstNum % 1 == 0.0) binding.tvResult.text = "${firstNum.toInt()}"
        else binding.tvResult.text = "${firstNum.toFloat()}"
    }

    private fun scaleButton(btn: View) {
        if (getString(R.string.theme) == "night") {
            btn.setBackgroundResource(R.drawable.btn_transition)
            anim = btn.background as TransitionDrawable
            anim.startTransition(1000)
            btn.startAnimation(AnimationUtils.loadAnimation(context,R.anim.scale))
        }
    }
}

package com.practicum.jointproject

import android.app.Dialog
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.get
import com.practicum.jointproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var anim: TransitionDrawable
    private var symbol = ""
    private var firstNum = 0.0
    private var secondNum = 0.0
    lateinit var menu: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menu = Dialog(this)

        binding.menu.setOnClickListener { showDialogMenu(binding.menu) }
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
        binding.delete.setOnClickListener {
            scaleButton(binding.delete)
            clearing() }
        binding.backspace.setOnClickListener {
            scaleButton(binding.backspace)
            binding.backspace.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale))
            binding.tvInput.text = binding.tvInput.text.dropLast(1)
        }

        binding.div.setOnClickListener { addSymbol(binding.div) }
        binding.mul.setOnClickListener { addSymbol(binding.mul) }
        binding.plus.setOnClickListener { addSymbol(binding.plus) }
        binding.minus.setOnClickListener { addSymbol(binding.minus) }
        binding.percent.setOnClickListener { addSymbol(binding.percent) }

        binding.equal.setOnClickListener {
            scaleButton(binding.equal)
            binding.equal.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale))
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
    }

    private fun showDialogMenu(v: View) {
        menu.setContentView(R.layout.menu_calculators)
        val oleg: FrameLayout = menu.findViewById(R.id.oleg)
        val rita: FrameLayout = menu.findViewById(R.id.rita)
        val petr: FrameLayout = menu.findViewById(R.id.petr)
        oleg.setOnClickListener {
            menu.dismiss()
            v.visibility = INVISIBLE
            Toast.makeText(this, "OlegActivity", Toast.LENGTH_LONG).show()
        }
        rita.setOnClickListener {
            menu.dismiss()
            v.visibility = INVISIBLE
            Toast.makeText(this, "RitaActivity", Toast.LENGTH_LONG).show()
        }
        petr.setOnClickListener {
            menu.dismiss()
            v.visibility = INVISIBLE
            Toast.makeText(this, "PetrActivity", Toast.LENGTH_LONG).show()
        }
        menu.show()
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
            btn.setBackgroundResource(R.drawable.neomorph_night_transition)
            anim = btn.background as TransitionDrawable
            anim.startTransition(900)
            btn.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale))
        } else {
            btn.setBackgroundResource(R.drawable.neomorph_day_transition)
            anim = btn.background as TransitionDrawable
            anim.startTransition(900)
            btn.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale))
        }
    }
}



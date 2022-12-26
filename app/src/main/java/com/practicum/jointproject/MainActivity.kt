package com.practicum.jointproject


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val top_ressum = findViewById<TextView>(R.id.top_ressum)
        val result_panel = findViewById<TextView>(R.id.result_panel)

        val zero = findViewById<Button>(R.id.zero)
        //val one = findViewById<Button>(R.id.one)
        val two = findViewById<Button>(R.id.two)
        val three = findViewById<Button>(R.id.three)
        val four = findViewById<Button>(R.id.four)
        val five = findViewById<Button>(R.id.five)
        val six = findViewById<Button>(R.id.six)
        val seven = findViewById<Button>(R.id.seven)
        val eight = findViewById<Button>(R.id.eight)
        val nine = findViewById<Button>(R.id.nine)
        val dota = findViewById<Button>(R.id.dota)
        val plus = findViewById<Button>(R.id.plus)
        val minus = findViewById<Button>(R.id.minus)
        val mul = findViewById<Button>(R.id.mul)
        val div = findViewById<Button>(R.id.div)
        val percent = findViewById<Button>(R.id.percent)
        val delete = findViewById<Button>(R.id.delete)
        val backspace = findViewById<ImageButton>(R.id.backspace)
        val result = findViewById<Button>(R.id.result)
        var res1 = 0.0
        var res2: Double
        var operator = ""




        zero.setOnClickListener { top_ressum.append("0") }
        //one.setOnClickListener { top_ressum.append("1") }
        two.setOnClickListener { top_ressum.append("2") }
        three.setOnClickListener { top_ressum.append("3") }
        four.setOnClickListener { top_ressum.append("4") }
        five.setOnClickListener { top_ressum.append("5") }
        six.setOnClickListener { top_ressum.append("6") }
        seven.setOnClickListener { top_ressum.append("7") }
        eight.setOnClickListener { top_ressum.append("8") }
        nine.setOnClickListener { top_ressum.append("9") }
        dota.setOnClickListener { top_ressum.append(".") }
        delete.setOnClickListener {
            top_ressum.text = ""
            result_panel.text = ""
        }
        backspace.setOnClickListener {
            val str = top_ressum.text.toString()
            if (str.isNotEmpty()) top_ressum.text = str.substring(0, str.length - 1)
        }

        plus.setOnClickListener {
            res1 = top_ressum.text.toString().toDouble()
            top_ressum.text = ""
            operator = "+"
        }
        minus.setOnClickListener {
            res1 = top_ressum.text.toString().toDouble()
            top_ressum.text = ""
            operator = "-"
        }
        mul.setOnClickListener {
            res1 = top_ressum.text.toString().toDouble()
            top_ressum.text = ""
            operator = "*"
        }
        div.setOnClickListener {
            res1 = top_ressum.text.toString().toDouble()
            top_ressum.text = ""
            operator = "/"
        }
        percent.setOnClickListener {

            res1 = top_ressum.text.toString().toDouble()
            top_ressum.text = ""
            operator = "%"

        }
        result.setOnClickListener {
            res2 = top_ressum.text.toString().toDouble()
            top_ressum.text = ""
            when (operator) {
                "+" -> result_panel.text = "${res1 + res2}"
                "-" -> result_panel.text = "${res1 - res2}"
                "*" -> result_panel.text = "${res1 * res2}"
                "/" -> result_panel.text = "${res1 / res2}"
                "%" -> result_panel.text = "${res1 / res2 * 100}"
            }

        }

    }

}

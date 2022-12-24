package com.practicum.jointproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.jointproject.adapter.HabitAdapter
import com.practicum.jointproject.model.Habit

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HabitAdapter
    var habitList: MutableList<Habit> = mutableListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val btnJustDoIt = findViewById<Button>(R.id.btn_do_it)
        btnJustDoIt.setOnClickListener {
            btnJustDoIt.text = "3дня 11:34" }*/

        habitList.add(Habit(
                "smoking",
                "Хочу меньше курить!",
                "Всего 12 сигарет за последний месяц",
                "Just do it",
                "Последний раз ты продержался 15 часов!"))
        habitList.add(Habit(
            "empty",
            "Хочу меньше xxxxxxxx!",
            "Всего х раз сорвался за последний месяц",
            "Just do it",
            "Последний раз ты продержался х часов!"))
        habitList.add(Habit(
            "empty",
            "Хочу меньше yyyyyyyy!",
            "Всего х раз сорвался за последний месяц",
            "Just do it",
            "Последний раз ты продержался х часов!"))

        setRecycler(habitList)
    }
    private fun setRecycler(habitList: List<Habit>) {
        var layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycler = findViewById(R.id.recycler_habits)
        recycler.layoutManager = layoutManager
        adapter = HabitAdapter(this, habitList)
        recycler.adapter = adapter
    }
}
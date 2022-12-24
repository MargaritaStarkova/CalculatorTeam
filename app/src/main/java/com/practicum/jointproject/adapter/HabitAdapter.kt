package com.practicum.jointproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practicum.jointproject.R
import com.practicum.jointproject.model.Habit


class HabitAdapter (
    var context: Context,
    var habits: List<Habit>): RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val habitItem: View = LayoutInflater
            .from(context)
            .inflate(R.layout.pattern_habit, parent, false)
        return HabitViewHolder(habitItem)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val imageId = context
            .resources
            .getIdentifier("ic_" + habits[position].image, "drawable", context.packageName)
        holder.image.setImageResource(imageId)
        holder.title.text = habits[position].title
        holder.progress.text = habits[position].progress
        holder.button.text = (habits[position].button)
        holder.motivation.text = (habits[position].motivation)
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val title: TextView
        val progress: TextView
        val button: TextView
        val motivation: TextView

        init {
            image = itemView.findViewById(R.id.img_main)
            title = itemView.findViewById(R.id.tv_title)
            progress = itemView.findViewById(R.id.tv_progress)
            button = itemView.findViewById(R.id.btn_do_it)
            motivation = itemView.findViewById(R.id.tv_motivation)
        }
    }

}
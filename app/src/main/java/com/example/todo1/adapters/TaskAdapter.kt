package com.example.todo1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo1.databinding.TaskItemLayoutBinding
import com.example.todo1.model.Task

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private var mTaskList = ArrayList<Task>()


    fun addTask(text: String){

        var task = Task(text)
        mTaskList.add(task)

        notifyItemInserted(itemCount)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        var bind = TaskItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TaskHolder(bind)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind.tvItem.text = mTaskList[position].title
    }

    override fun getItemCount(): Int = mTaskList.size

    inner class TaskHolder(var bind: TaskItemLayoutBinding) : RecyclerView.ViewHolder(bind.root)
}
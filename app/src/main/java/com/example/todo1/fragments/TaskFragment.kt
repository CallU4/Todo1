package com.example.todo1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo1.MainActivity
import com.example.todo1.R
import com.example.todo1.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private lateinit var bind: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentTaskBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.cvTask.bringToFront()
        bind.button.setOnClickListener {
            var main = activity as MainActivity

            val title = bind.etTitle.text
            main.onAddTask(title.toString())
        }
    }

    interface OnAddTask {
        fun onAddTask(text: String)
    }
}
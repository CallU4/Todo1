package com.example.todo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo1.adapters.TaskAdapter
import com.example.todo1.databinding.ActivityMainBinding
import com.example.todo1.fragments.TaskFragment
import com.example.todo1.model.Book
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), TaskFragment.OnAddTask {

    private lateinit var bind: ActivityMainBinding

    private var adapter = TaskAdapter()
    private var transaction = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.rvTodo.layoutManager = LinearLayoutManager(this)
        bind.rvTodo.adapter = adapter

        bind.btnCallFrag.setOnClickListener {
            transaction.beginTransaction().replace(R.id.fragTask, TaskFragment()).commit()
        }



    }



    override fun onAddTask(text: String) {
        println("Added Task")
        adapter.addTask(text)
    }


    fun updateData(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gutendex.com")
            .build()

        val service = retrofit.create(BookApi.getData())
    }
}
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            transaction.beginTransaction().addToBackStack(null)
                .replace(R.id.fragTask, TaskFragment()).commit()
        }

        CoroutineScope(IO).launch {
            while(true){

            delay(1000)
            updateData()
            }
        }

        CoroutineScope(IO).launch{
            while(true){

            delay(2000)
                CoroutineScope(Main).launch{

            onAddTask(Thread.currentThread().isAlive.toString())
                }
            }
        }

    }


    override fun onAddTask(text: String) {
        println("Added Task")
        adapter.addTask(text)
    }


    fun updateData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gutendex.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BookApi::class.java)

        val call = service.getData()
        call.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {

                val body = response.body()!!
                val stringBuilder = "Book: ${body.get(1).title}"

                bind.tvData.text = stringBuilder
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                println("Something went wrong")
            }
        })
    }
}

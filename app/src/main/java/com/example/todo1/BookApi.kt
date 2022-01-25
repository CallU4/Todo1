package com.example.todo1

import com.example.todo1.model.Book
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {

    @GET("/books")
    fun getData(): Call<List<Book>>
}
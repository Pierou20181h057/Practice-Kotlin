package com.example.appconsumoapitest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeInterface {
    @GET ("api")
    fun  getJoke(@Query("format") format: String): Call<Joke>
}
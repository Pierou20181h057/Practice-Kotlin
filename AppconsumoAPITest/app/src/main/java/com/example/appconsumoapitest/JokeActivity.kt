package com.example.appconsumoapitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class JokeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)
        val btBroma = findViewById<Button>(R.id.bt_Broma)
        btBroma.setOnClickListener{
            loadJoke()
        }
    }

    private fun loadJoke() {

        val tvBroma = findViewById<TextView>(R.id.tv_broma)
        //tvBroma.text = "Broma encontrada!!"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jokeService: JokeInterface
        jokeService = retrofit.create(JokeInterface::class.java)
        val request =  jokeService.getJoke("json")

        request.enqueue(object : Callback<Joke>{
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful) {
                    tvBroma.text = response.body()!!.joke
                }
            }
            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Log.d("jokeActivity", t.toString())
            }
        })
    }
}
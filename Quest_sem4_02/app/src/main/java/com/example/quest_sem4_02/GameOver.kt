package com.example.quest_sem4_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val message = intent.getStringExtra(EXTRA_MESS)
        val tvpunta = findViewById<TextView>(R.id.tv_puntaje)
        tvpunta.text = message
    }
    fun retryGame(view: View){
        val intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
    }
}


package com.example.quest_sem4_02

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ImageView
import android.widget.ProgressBar

const val EXTRA_MESS= "com.example.myapplication"

class MainActivity : AppCompatActivity() {
    lateinit var questions: ArrayList<Question>
    var position = 0
    var vida= 3

    var comodin = 3
    var progre =0
    var correct= 0
    var puntaje = 100
    var total = 0
    private val handler = Handler()

    /*val corazon = resources.getDrawable(R.drawable.corazon, null)*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadQuestions()
        setupViews()
        prog()
    }

    private fun prog() {
        val pb = findViewById<ProgressBar>(R.id.pb_timer)
        progre = pb.progress
        Thread(Runnable{
            while (progre < 250) {
                progre += 1
                // Update the progress bar and display the current value
                handler.post(Runnable {
                    pb.progress = progre
                    // setting current progress to the textview

                })
                try {
                    Thread.sleep(250)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            gameover()

        }).start()
    }

    private fun loadQuestions() {

        questions = ArrayList()
        var question = Question("El rollo de canela esta hecho en paprica",false)
        questions.add(question)

        questions.add(Question("Flutter puede hacer render\nde 120 fotogramas",true))
        questions.add(Question("Flutter se lanzo en 4\nenero de 2018",false))
        questions.add(Question("Xamari usa un unico\nlenguaje, c++",false))
        questions.add(Question("React Native manipula el\nDOM atra vez de un DOM\nvirtual",false))

    }
    private fun validator_gameover(){
        if (vida <= 0){
            gameover()
        }
    }
    private fun gameover(){


        val intent = Intent( this, GameOver::class.java).apply {
            putExtra(EXTRA_MESS,total.toString())
        }
        startActivity(intent)
    }
    private fun validator_comodin(){
        if (comodin <= 0){
            val bt_next = findViewById<Button>(R.id.btNext)
            bt_next.isEnabled = false
        }

    }
    private fun Fun_Puntaje(){
        val tv_puntaje = findViewById<TextView>(R.id.tv_Puntaje)
        val tv_puch = findViewById<TextView>(R.id.tv_puch)
        if(correct == 0){
            tv_puch.text = "Meh"
            total += puntaje * 0
            tv_puntaje.text = total.toString()
        }
        if (correct == 1){
            tv_puch.text = "Ok"
            total += puntaje * 1
            tv_puntaje.text = total.toString()
        }
        if (correct == 2){
            tv_puch.text = "Good"
            total += puntaje * 2
            tv_puntaje.text = total.toString()
        }
        if (correct >= 3){
            tv_puch.text = "Perfect"
            total += puntaje * 3
            tv_puntaje.text = total.toString()
        }

    }


    private fun setupViews() {
        var countText = getString(R.string.btn_next, comodin)
        val bt_next = findViewById<Button>(R.id.btNext)
        bt_next.text = countText
        val btSi = findViewById<Button>(R.id.btSI)
        val btNo = findViewById<Button>(R.id.btNO)
        val tvQuestion = findViewById<TextView>(R.id.tvQuest)
        val btSiguiente = findViewById<Button>(R.id.btNext)
        val vidastext = findViewById<TextView>(R.id.tv_corazon)
        val drawer = findViewById<ImageView>(R.id.Corazones)
        tvQuestion.text = questions[position].sentence
        drawer.setImageResource(R.drawable.corazon)
        btSi.setOnClickListener{
            if (questions[position].answer == true){
                Toast.makeText(this, "Rpta correcta", Toast.LENGTH_LONG).show()
                correct++
                Fun_Puntaje()
                position++
                tvQuestion.text = questions[position].sentence
            }
            else{
                Toast.makeText(this, "Rpta incorrecta", Toast.LENGTH_LONG).show()
                vida--
                correct = 0
                validator_gameover()
                vidastext.text = vida.toString()
                Fun_Puntaje()
            }

        }
        btNo.setOnClickListener{
            if (questions[position].answer == false){
                Toast.makeText(this, "Rpta correcta", Toast.LENGTH_LONG).show()
                correct++
                Fun_Puntaje()
                position++
                tvQuestion.text = questions[position].sentence
            }
            else{
                Toast.makeText(this, "Rpta incorrecta", Toast.LENGTH_LONG).show()
                vida--
                correct = 0
                validator_gameover()
                vidastext.text = vida.toString()
                Fun_Puntaje()
            }
        }
        btSiguiente.setOnClickListener{
            position++
            tvQuestion.text = questions[position].sentence
            comodin--
            validator_comodin()
            countText = getString(R.string.btn_next, comodin)
            bt_next.text = countText


        }



    }
}
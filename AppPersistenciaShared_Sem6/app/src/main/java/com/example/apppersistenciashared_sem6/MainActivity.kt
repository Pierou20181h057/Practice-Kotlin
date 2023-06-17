package com.example.apppersistenciashared_sem6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = SharedPreferences(this)
        val btgrabar = findViewById<Button>(R.id.bt_Grabar)
        btgrabar.setOnClickListener{
            val etName =findViewById<TextInputEditText>(R.id.et_Name)
            val name = etName.text.toString()
            sharedPreferences.save("name", name)

            Toast.makeText(this,"Data stored", Toast.LENGTH_LONG).show()
            val btretrieve = findViewById<Button>(R.id.bt_Retrieve)
            btretrieve.setOnClickListener{
                if (sharedPreferences.getValue("name")!=null){
                    val tvRetrieve = findViewById<TextView>(R.id.tvRetrieve)
                    tvRetrieve.setText(sharedPreferences.getValue("name"))
                }
            }
        }
    }
}
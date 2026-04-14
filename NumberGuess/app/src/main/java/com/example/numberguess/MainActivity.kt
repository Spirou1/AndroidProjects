package com.example.numberguess

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var sortedNumber: Int = 0
    private var tries: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sortedNumber = (1..100).random()
        tries = 0
        println("SORTED NUMBER: $sortedNumber")
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun guess(view: View) {
        val input = findViewById<EditText>(R.id.editTextNumber)
        val output = findViewById<TextView>(R.id.textView2)
        var tip: String = "Numero sorteado eh maior!!!"


        val inputNumber: Int = input.text.toString().toInt()
        tries++

        if (sortedNumber < inputNumber) {
            tip = "Numero sorteado eh menor!!!"
        } else if (sortedNumber == inputNumber){
            tip = "PARABENS TU EH PICA, ACERTOU!!!! \nVoce foi monstro e usou $tries tentativas"
        }
        output.text = tip
    }
}
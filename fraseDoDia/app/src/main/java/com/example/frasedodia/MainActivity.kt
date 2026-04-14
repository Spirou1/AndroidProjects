package com.example.frasedodia

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun quote(view: View) {
        val quotes = arrayOf(
            "batatinha",
            "aaaaaaaaaaaaaaaaaaaa",
        "frase legal",
        "mobileeeeeeeeeeeeee",
        "ovoooooooooooooooooooooo",
        "melanciaaaaaaaaaaaaaaaaa",
        )

        val randomIndex = (0..6).random()
        val randomQuote = findViewById<TextView>(R.id.textViewOutput)
        randomQuote.text = quotes[randomIndex]
    }
}


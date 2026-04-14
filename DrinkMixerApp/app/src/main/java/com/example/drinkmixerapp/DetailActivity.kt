package com.example.drinkmixerapp

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val drink = intent.getSerializableExtra("DRINK_OBJETO") as? Drink
        val nomeDrink = findViewById<TextView>(R.id.textViewNome)
        val ingredientes = findViewById<TextView>(R.id.textViewIngredientes)
        val preparo = findViewById<TextView>(R.id.textViewPreparo)



        nomeDrink.setText(drink?.nome)
        ingredientes.setText(drink?.ingredientes?.joinToString("\n"))
        preparo.setText(drink?.preparo)

    }
}
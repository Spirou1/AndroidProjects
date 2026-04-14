package com.example.cambioapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

    fun convert(view: View) {
        val numeroInicial = findViewById<EditText>(R.id.editTextNumberDecimal)
        val numeroConvertido = findViewById<TextView>(R.id.textViewNumero)

        if (numeroInicial.length() == 0) {
            Toast.makeText(this, "informe um valor", Toast.LENGTH_SHORT).show()
        }

        val valor = numeroInicial.text.toString().toDouble()
        val real = valor * 5.5
        numeroConvertido.text = "$real reais"
    }
}
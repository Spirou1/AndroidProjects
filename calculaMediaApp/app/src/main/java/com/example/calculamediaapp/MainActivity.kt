package com.example.calculamediaapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
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

    fun calculaMedia(view: View) {
        val editNome = findViewById<EditText>(R.id.editTextNome)
        val editNota1 = findViewById<EditText>(R.id.editTextNota1)
        val editNota2 = findViewById<EditText>(R.id.editTextNota2)
        val editFreq = findViewById<EditText>(R.id.editTextFrequencia)

        if (editNome.text.isEmpty() || editNota1.text.isEmpty() ||
            editNota2.text.isEmpty() || editFreq.text.isEmpty()) {

            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        } else {
            val nome = editNome.text.toString()
            val nota1 = editNota1.text.toString().toDouble()
            val nota2 = editNota2.text.toString().toDouble()
            val frequencia = editFreq.text.toString().toInt()
            val mediaFinal = (nota1 + nota2) / 2

            val aluno = Aluno(nome, nota1, nota2, frequencia, mediaFinal)

            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("ALUNO", aluno)
            startActivity(intent)
        }
    }
}
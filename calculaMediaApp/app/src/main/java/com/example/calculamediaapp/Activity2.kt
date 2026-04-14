package com.example.calculamediaapp

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val aluno = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("ALUNO", Aluno::class.java)
        } else {
            intent.getSerializableExtra("ALUNO") as Aluno
        }

        val nome = aluno?.nome
        val nota1 = aluno?.nota1
        val nota2 = aluno?.nota2
        val frequencia = aluno?.frequencia
        val mediaFinal = aluno?.mediaFinal

        val situacao: String = when {
            frequencia!! < 75 -> "Reprovado por falta"
            mediaFinal!! < 4 -> "Reprovado por nota"
            mediaFinal < 7 -> "Final"
            else -> "Aprovado"
        }

        findViewById<TextView>(R.id.textViewNome).text = "$nome"
        findViewById<TextView>(R.id.textViewNota1).text = "$nota1"
        findViewById<TextView>(R.id.textViewNota2).text = "$nota2"
        findViewById<TextView>(R.id.textViewFrequencia).text = " $frequencia"
        findViewById<TextView>(R.id.textViewMediaFinal).text = " $mediaFinal + $situacao"

    }
}
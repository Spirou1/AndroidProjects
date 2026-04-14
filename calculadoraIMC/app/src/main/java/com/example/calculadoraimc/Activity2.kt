package com.example.calculadoraimc

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val textViewIMC = findViewById<TextView>(R.id.textViewIMC)
        val textViewFrase = findViewById<TextView>(R.id.textViewFrase)

        val paciente = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_PACIENTE", Paciente::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Paciente>("EXTRA_PACIENTE")
        }

        if (paciente != null) {
            val textoResultado = paciente.imc.toString()


            if (textViewIMC != null) {
                textViewIMC.text = textoResultado
                textViewFrase?.text = when {
                    paciente.imc < 18.5 -> "Abaixo do peso"
                    paciente.imc < 25.0 -> "Peso normal"
                    paciente.imc < 30.0 -> "Sobrepeso"
                    paciente.imc < 35.0 -> "Obesidade Grau I"
                    paciente.imc < 40.0 -> "Obesidade Grau II"
                    else -> "Obesidade Grau III (Mórbida)"
                }
            }
        } else {
            if (textViewIMC != null) {
                textViewIMC.text = "Erro ao carregar os dados do paciente."
            }
        }
    }
}

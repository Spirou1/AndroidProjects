package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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

    fun calc_imc(view: View) {
        val intent = Intent(this, Activity2::class.java)

        val editNome = findViewById<EditText>(R.id.editTextText)
        val editPeso = findViewById<EditText>(R.id.editTextNumber2)
        val editAltura = findViewById<EditText>(R.id.editTextNumber)

        val nome = editNome.text.toString()
        val pesoString = editPeso.text.toString().replace(",", ".")
        val alturaString = editAltura.text.toString().replace(",", ".")

        val peso = pesoString.toDoubleOrNull() ?: 0.0
        var altura = alturaString.toDoubleOrNull() ?: 0.0

            if (altura > 0) {
                val imc = peso / (altura * altura) * 10000
                val paciente = Paciente(nome, peso, altura, imc)
                
                intent.putExtra("EXTRA_PACIENTE", paciente)
                startActivity(intent)
            } else {
                Toast.makeText(this, "A altura deve ser maior que zero.", Toast.LENGTH_SHORT).show()
            }

    }
}

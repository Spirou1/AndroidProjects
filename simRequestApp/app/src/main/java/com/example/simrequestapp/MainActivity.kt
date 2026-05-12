package com.example.simrequestapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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


    fun request(view: View) {
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        tvStatus.text = "Status: Iniciando acesso à API..."

        progressBar.visibility = ProgressBar.VISIBLE
// Inicia a corrotina no escopo do ciclo de vida (lifecycleScope)
        lifecycleScope.launch(Dispatchers.Main) {
// Simula uma chamada de API com delay de 2 segundos
            delay(2000)
// Esconde o ProgressBar
            progressBar.visibility = ProgressBar.GONE
// Atualiza o status após a simulação
            tvStatus.text = "Status: Acesso à API finalizado!"
        }
    }
}

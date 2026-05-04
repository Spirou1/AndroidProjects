package com.example.cronometroapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var tvStatusArroz: TextView
    lateinit var tvStatusFeijao: TextView

    private var jobArroz: Job? = null
    private var jobFeijao: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tvStatusArroz = findViewById(R.id.tvStatusArroz)
        tvStatusFeijao = findViewById(R.id.tvStatusFeijao)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarCozimento(view: View) {
        view.isEnabled = false

        jobArroz = lifecycleScope.launch {
            cozinharArroz()
        }

        jobFeijao = lifecycleScope.launch {
            cozinharFeijao()
        }
    }

    fun cancelarCozimento(view: View) {
        jobArroz?.cancel()
        jobFeijao?.cancel()

        tvStatusArroz.text = "Arroz: cancelado"
        tvStatusFeijao.text = "Feijão: cancelado"

        var btnIniciar = findViewById<Button>(R.id.btnIniciar)
        btnIniciar.isEnabled = true
    }

    suspend fun cozinharArroz() {
        tvStatusArroz.text = "Arroz: Lavando o arroz..."
        delay(1000)
        tvStatusArroz.text = "Arroz: Colocando na panela..."
        delay(1000)
        tvStatusArroz.text = "Arroz: Cozinhando..."
        delay(3000)
        tvStatusArroz.text = "Arroz: Pronto!"
    }

    suspend fun cozinharFeijao() {
        tvStatusFeijao.text = "Feijão: Escolhendo feijão..."
        delay(1000)
        tvStatusFeijao.text = "Feijão: Lavando..."
        delay(1000)
        tvStatusFeijao.text = "Feijão: Cozinhando..."
        delay(3000)
        tvStatusFeijao.text = "Feijão: Pronto!"
    }
}
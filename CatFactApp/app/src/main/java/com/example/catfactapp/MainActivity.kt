package com.example.catfactapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class CatFactResponse(
    @SerializedName("fact") val fact: String,
    @SerializedName("length") val length: Int
)


interface CatFactApi {
    @GET("fact")
    suspend fun getCatFact(): CatFactResponse
}

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var catFactApi: CatFactApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)
        val btnGetFact = findViewById<Button>(R.id.btnGetFact)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        catFactApi = retrofit.create(CatFactApi::class.java)

        btnGetFact.setOnClickListener {
            getFact()
        }
    }

    private fun getFact() {
        progressBar.visibility = View.VISIBLE
        tvResult.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val factResponse = withContext(Dispatchers.IO) {
                    catFactApi.getCatFact()
                }
                progressBar.visibility = View.GONE
                tvResult.visibility = View.VISIBLE
                tvResult.text = factResponse.fact
            } catch (e: Exception) {
                Log.e("Erro", e.message ?: "Erro desconhecido", e)
                tvResult.text = "Erro ao buscar fato"
            }
        }
    }
}
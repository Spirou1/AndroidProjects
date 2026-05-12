package com.example.downloadimageapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var statusTextView: TextView
    private lateinit var loadButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        progressBar = findViewById(R.id.progressBar)
        statusTextView = findViewById(R.id.statusTextView)
        loadButton = findViewById(R.id.loadButton)

        loadButton.setOnClickListener {
            downloadImage()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun downloadImage() {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.visibility = View.VISIBLE
            statusTextView.text = "Carregando..."
            loadButton.isEnabled = false

            val bitmap = withContext(Dispatchers.IO) {
                try {
                    val url = URL("https://picsum.photos/400")
                    BitmapFactory.decodeStream(url.openStream())
                } catch (e: Exception) {
                    null
                }
            }

            progressBar.visibility = View.GONE
            loadButton.isEnabled = true

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
                statusTextView.text = "Imagem carregada!"
            } else {
                statusTextView.text = "Erro ao carregar imagem!"
            }
        }
    }
}

package com.example.drinkmixerapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MasterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val listView = findViewById<ListView>(R.id.listView)
        val drinks = listOf(
            Drink(
                "Quentao",
                listOf("limao", "cachaca", "agua"),
                "joga tudo no copo e mistura"
            ),
            Drink(
                "Copao de bandido",
                listOf("gelo de coco", "uisque", "energetico"),
                "joga tudo no copo e boa"
            ),
            Drink(
                "Drink dos deuses",
                listOf("agua", "nao sei", "nao sei tb..."),
                "so jogar no copo e tomar, sem erro"
            )
        )
        drinks.toList()
        val nomesDosDrinks = drinks.map { it.nome }

        val arrayAdapter = ArrayAdapter(
            this, R.layout.list_item_custom, nomesDosDrinks
        )

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { adapterView, view, position, id ->
            val drinkEscolhido = drinks[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("DRINK_OBJETO", drinkEscolhido)
            startActivity(intent)
        }
    }
}
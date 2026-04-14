package com.example.heroesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MasterActivity : AppCompatActivity() {
    private lateinit var recyclerViewHeroes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_master)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerViewHeroes = findViewById(R.id.heroesRV)
        recyclerViewHeroes.adapter = HeroesAdapter(this.createHeroes(), this) {
            Toast.makeText(this, "Clicou no ${it.heroName}", Toast.LENGTH_SHORT).show()
        }
        recyclerViewHeroes.layoutManager = LinearLayoutManager(this)
        recyclerViewHeroes.setHasFixedSize(true)
        recyclerViewHeroes.addItemDecoration(
            DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun createHeroes(): List<Hero> {
        return listOf(
            Hero(R.drawable.batman, "Batman", "DC COMICS"),
            Hero(R.drawable.hulk, "Hulk", "Marvel"),
            Hero(R.drawable.flash, "Flash", "DC COMICS"),
            Hero(R.drawable.drstrange, "Doutor Estranho", "Marvel"),
            Hero(R.drawable.superman, "Super Homi", "DC COMICS"),
            Hero(R.drawable.ironman, "Homi de Ferro", "Marvel")
        )
    }
}
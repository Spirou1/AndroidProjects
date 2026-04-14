package com.example.harrypotterdb.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.harrypotterdb.R
import com.example.harrypotterdb.data.dao.HPCharDAO
import com.example.harrypotterdb.model.HPChar

class MainActivity : AppCompatActivity() {

    private lateinit var lvChars: ListView
    private lateinit var tvEmpty: TextView
    private lateinit var dao: HPCharDAO
    private lateinit var adapter: ArrayAdapter<HPChar>
    private var charList: List<HPChar> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvChars = findViewById(R.id.lvChars)
        tvEmpty = findViewById(R.id.tvEmpty)
        dao = HPCharDAO(this)

        // Ao clicar em um item da lista, abre para editar
        lvChars.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val charSelected = charList[position]
            val intent = Intent(this, NewHPChar::class.java)
            intent.putExtra("CHAR_ID", charSelected.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList() {
        charList = dao.getAllChars()
        if (charList.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            lvChars.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            lvChars.visibility = View.VISIBLE
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, charList)
            lvChars.adapter = adapter
        }
    }

    fun newChar(view: View) {
        val intent = Intent(this, NewHPChar::class.java)
        startActivity(intent)
    }
}
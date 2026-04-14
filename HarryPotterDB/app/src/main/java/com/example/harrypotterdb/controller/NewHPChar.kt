package com.example.harrypotterdb.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.harrypotterdb.R
import com.example.harrypotterdb.data.dao.HPCharDAO
import com.example.harrypotterdb.model.HPChar

class NewHPChar : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etHouse: EditText
    private lateinit var etAncestry: EditText
    private lateinit var btnSave: Button
    private lateinit var dao: HPCharDAO
    private var charId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_hpchar)

        etName = findViewById(R.id.etName)
        etHouse = findViewById(R.id.etHouse)
        etAncestry = findViewById(R.id.etAncestry)
        btnSave = findViewById(R.id.btnSave)
        dao = HPCharDAO(this)

        // Verifica se estamos editando um personagem existente
        charId = intent.getIntExtra("CHAR_ID", -1)
        if (charId != -1) {
            loadCharData(charId)
        }

        btnSave.setOnClickListener {
            saveChar()
        }
    }

    private fun loadCharData(id: Int) {
        val hpChar = dao.getCharById(id)
        hpChar?.let {
            etName.setText(it.name)
            etHouse.setText(it.house)
            etAncestry.setText(it.ancestry)
        }
    }

    private fun saveChar() {
        val name = etName.text.toString()
        val house = etHouse.text.toString()
        val ancestry = etAncestry.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(this, "O nome é obrigatório", Toast.LENGTH_SHORT).show()
            return
        }

        val hpChar = HPChar(id = if (charId == -1) 0 else charId, name = name, house = house, ancestry = ancestry)

        val result = if (charId == -1) {
            dao.addHPChar(hpChar)
        } else {
            dao.editChar(hpChar).toLong()
        }

        if (result > -1) {
            Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.persistenciadb1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etLogin: EditText = findViewById(R.id.etLogin)
        val btnSave: Button = findViewById(R.id.btnSave)
        val tvUsers : TextView = findViewById(R.id.tvUsers)

        dbHelper = DBHelper(this)

        btnSave.setOnClickListener {
            val login = etLogin.text.toString()
            dbHelper.addUser(login)
            getAllUsers(tvUsers)
        }

        getAllUsers(tvUsers)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getAllUsers(tvUsers: TextView) {
        val users = dbHelper.getAllUsers()
        tvUsers.text = users.joinToString("\n")
    }
}
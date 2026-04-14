package com.example.persistenciadb1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "AppDB.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "users"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = "CREATE TABLE $TABLE_NAME " +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT, Login TEXT)"
        db.execSQL(createUsersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(login: String) {
        val db = this.writableDatabase
        val register = ContentValues().apply {
            put("Login", login)
        }
        db.insert(TABLE_NAME, null, register)
        db.close()
    }

    fun getAllUsers(): List<String> {
        val users = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT Login FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                users.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return users
    }
}

package com.example.harrypotterdb.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.harrypotterdb.data.db.DBHelper
import com.example.harrypotterdb.model.HPChar

class HPCharDAO (private val context: Context) {
    private val dbHelper = DBHelper(context)

    fun addHPChar(hpChar: HPChar): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBHelper.COLUMN_NAME, hpChar.name)
            put(DBHelper.COLUMN_HOUSE, hpChar.house)
            put(DBHelper.COLUMN_ANCESTRY, hpChar.ancestry)
        }
        val id = db.insert(DBHelper.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getAllChars(): List<HPChar> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)
        val charList = mutableListOf<HPChar>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME))
                val house = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_HOUSE))
                val ancestry = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ANCESTRY))
                charList.add(HPChar(id, name, house, ancestry))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return charList
    }

    fun getCharById(id: Int): HPChar? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DBHelper.TABLE_NAME, 
            null, 
            "${DBHelper.COLUMN_ID}=?", 
            arrayOf(id.toString()), 
            null, null, null
        )

        var hpChar: HPChar? = null
        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME))
            val house = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_HOUSE))
            val ancestry = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ANCESTRY))
            hpChar = HPChar(id, name, house, ancestry)
        }
        cursor.close()
        db.close()
        return hpChar
    }

    fun editChar(hpChar: HPChar): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBHelper.COLUMN_NAME, hpChar.name)
            put(DBHelper.COLUMN_HOUSE, hpChar.house)
            put(DBHelper.COLUMN_ANCESTRY, hpChar.ancestry)
        }
        val rowsAffected = db.update(
            DBHelper.TABLE_NAME, 
            values, 
            "${DBHelper.COLUMN_ID}=?", 
            arrayOf(hpChar.id.toString())
        )
        db.close()
        return rowsAffected
    }

    fun deleteChar(id: Int): Int {
        val db = dbHelper.writableDatabase
        val rowsDeleted = db.delete(
            DBHelper.TABLE_NAME, 
            "${DBHelper.COLUMN_ID}=?", 
            arrayOf(id.toString())
        )
        db.close()
        return rowsDeleted
    }
}
package com.Barzola.primeraclecssilla
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "correo TEXT UNIQUE, " +
                    "contrasena TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun insertarUsuario(nombre: String, correo: String, contrasena: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("correo", correo)
        values.put("contrasena", contrasena)
        val result = db.insert("usuarios", null, values)
        return result != -1L
    }
}
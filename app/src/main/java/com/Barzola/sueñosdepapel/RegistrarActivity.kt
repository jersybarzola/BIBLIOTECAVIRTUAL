package com.Barzola.sueñosdepapel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar2)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnLoginR = findViewById<Button>(R.id.btnLoginR)

        val dbHelper = DBHelper(this)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val insertado = dbHelper.insertarUsuario(nombre, correo, contrasena)

            if (insertado) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Login::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error: el correo ya está registrado", Toast.LENGTH_SHORT).show()
            }
        }

        btnLoginR.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }
}
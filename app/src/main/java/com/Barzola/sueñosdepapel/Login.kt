package com.Barzola.sueñosdepapel
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreoLogin = findViewById<EditText>(R.id.etCorreoLogin)
        val etContrasenaLogin = findViewById<EditText>(R.id.etContrasenaLogin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnIrRegistro = findViewById<Button>(R.id.btnIrRegistro)

        val dbHelper = DBHelper(this)

        btnLogin.setOnClickListener {
            val correo = etCorreoLogin.text.toString().trim()
            val contrasena = etContrasenaLogin.text.toString().trim()

            if (correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.readableDatabase
            val cursor: Cursor = db.rawQuery(
                "SELECT nombre FROM usuarios WHERE correo=? AND contrasena=?",
                arrayOf(correo, contrasena)
            )

            if (cursor.moveToFirst()) {
                val nombreUsuario = cursor.getString(0)
                cursor.close()
                Toast.makeText(this, "Bienvenido, $nombreUsuario", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("nombre_usuario", nombreUsuario)
                startActivity(intent)
                finish()
            } else {
                cursor.close()
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        btnIrRegistro.setOnClickListener {

            startActivity(Intent(this, RegistrarActivity::class.java))
        }
    }
}
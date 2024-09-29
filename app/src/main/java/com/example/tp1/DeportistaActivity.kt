package com.example.tp1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DeportistaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deportista)

        // Recuperar los datos del intent
        val nombre = intent.getStringExtra("nombre")
        val deporte = intent.getStringExtra("deporte")
        val enActividad = intent.getBooleanExtra("enActividad", false)
        val imagenResId = intent.getIntExtra("imagenResId", R.drawable.ic_launcher_foreground)

        // Enlazar los TextViews desde el layout
        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        val textViewDeporte = findViewById<TextView>(R.id.textViewDeporte)
        val textViewActividad = findViewById<TextView>(R.id.textViewActividad)
        val imageViewDeportista = findViewById<ImageView>(R.id.imageViewDeportista)
        val buttonVolver = findViewById<Button>(R.id.buttonVolver) // Enlazar el botón

        // Asignar los datos recuperados a los TextViews
        textViewNombre.text = "Nombre: $nombre"
        textViewDeporte.text = "Deporte: $deporte"
        textViewActividad.text = "En actividad: ${if (enActividad) "Sí" else "No"}"

        // Asignar la imagen al ImageView usando el ID de recurso
        imageViewDeportista.setImageResource(imagenResId)

        // Asignar el listener al botón Volver
        buttonVolver.setOnClickListener {
            finish() // Finaliza la actividad actual y regresa a la anterior
        }
    }
}


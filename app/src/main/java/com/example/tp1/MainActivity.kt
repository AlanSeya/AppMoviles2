package com.example.tp1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var listView: ListView

    // Datos de deportistas (nombre, deporte, está en actividad)
    private val deportistas = mapOf(
        "Argentina" to listOf(
            Deportista("Lionel Messi", "Fútbol", true, R.drawable.messi),
            Deportista("Manu Ginóbili", "Básquet", false, R.drawable.ginobili),
            Deportista("Diego Maradona", "Fútbol", false, R.drawable.maradona),
            Deportista("Juan Martín del Potro", "Tenis", false, R.drawable.delpotro),
            Deportista("Gabriel Batistuta", "Fútbol", false, R.drawable.batistuta),
            Deportista("Luciana Aymar", "Hockey", false, R.drawable.aymar),
            Deportista("Sergio Aguero", "Fútbol", true, R.drawable.aguero),
            Deportista("Ezequiel Lavezzi", "Fútbol", false, R.drawable.lavezzi),
            Deportista("Juan Pablo Sorín", "Fútbol", false, R.drawable.sorin),
            Deportista("Paola Suárez", "Tenis", false, R.drawable.suarez)
        ),
        "Brasil" to listOf(
            Deportista("Pelé", "Fútbol", false, R.drawable.pele),
            Deportista("Neymar", "Fútbol", true, R.drawable.neymar),
            Deportista("Rivaldo", "Fútbol", false, R.drawable.rivaldo),
            Deportista("Ronaldinho", "Fútbol", false, R.drawable.ronaldinho),
            Deportista("Kaká", "Fútbol", false, R.drawable.kaka),
            Deportista("Gustavo Kuerten", "Tenis", false, R.drawable.kuerten),
            Deportista("Marta", "Fútbol", true, R.drawable.marta),
            Deportista("Vitor Belfort", "MMA", false, R.drawable.belfort),
            Deportista("Fernando Alonso", "F1", true, R.drawable.alonso),
            Deportista("Thiago Silva", "Fútbol", true, R.drawable.silva)
        ),
        "España" to listOf(
            Deportista("Rafael Nadal", "Tenis", true, R.drawable.nadal),
            Deportista("Pau Gasol", "Básquet", false, R.drawable.gasol),
            Deportista("Iker Casillas", "Fútbol", false, R.drawable.casillas),
            Deportista("Fernando Torres", "Fútbol", false, R.drawable.torres),
            Deportista("Carles Puyol", "Fútbol", false, R.drawable.puyol),
            Deportista("David Villa", "Fútbol", false, R.drawable.villa),
            Deportista("Alberto Contador", "Ciclismo", false, R.drawable.contador),
            Deportista("Mireia Belmonte", "Natación", true, R.drawable.belmonte),
            Deportista("Garbiñe Muguruza", "Tenis", true, R.drawable.muguruza),
            Deportista("Sergio Ramos", "Fútbol", true, R.drawable.ramos)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        listView = findViewById(R.id.listView)

        // Lista de países con banderas
        val paises = listOf(
            Pais("Selecciona un país", 0),
            Pais("Argentina", R.drawable.arg),
            Pais("Brasil", R.drawable.br),
            Pais("España", R.drawable.esp)
        )

        // Usa el adaptador personalizado
        val paisAdapter = PaisAdapter(this, paises)
        spinner.adapter = paisAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCountry = paises[position]

                // Obtener los deportistas del país seleccionado
                val deportistasDelPais = deportistas[selectedCountry.nombre]?.map { it.nombre } ?: emptyList()

                // Actualizar el ListView con los deportistas
                val listAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, deportistasDelPais)
                listView.adapter = listAdapter

                // Evento click en deportista
                listView.setOnItemClickListener { _, _, position, _ ->
                    val deportistaSeleccionado = deportistas[selectedCountry.nombre]?.get(position)

                    // Navegación a la nueva actividad
                    val intent = Intent(this@MainActivity, DeportistaActivity::class.java).apply {
                        putExtra("nombre", deportistaSeleccionado?.nombre)
                        putExtra("deporte", deportistaSeleccionado?.deporte)
                        putExtra("enActividad", deportistaSeleccionado?.enActividad)
                        putExtra("imagenResId", deportistaSeleccionado?.imagenResId)
                    }
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada si no se selecciona nada
            }
        }
    }
}

// Clase Deportista para almacenar la información de cada deportista
data class Deportista(val nombre: String, val deporte: String, val enActividad: Boolean, val imagenResId: Int)

// Clase País que contendrá el nombre y la imagen de la bandera
data class Pais(val nombre: String, val banderaResId: Int)

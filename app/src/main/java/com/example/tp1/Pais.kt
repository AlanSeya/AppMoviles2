package com.example.tp1 // Cambia esto al nombre de tu paquete

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class PaisAdapter(context: Context, private val paises: List<Pais>) :
    ArrayAdapter<Pais>(context, 0, paises) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false)
        val pais = getItem(position)

        val imageViewBandera = view.findViewById<ImageView>(R.id.imageViewBandera)
        val textViewPais = view.findViewById<TextView>(R.id.textViewPais)

        if (pais != null) {
            imageViewBandera.setImageResource(pais.banderaResId)
            textViewPais.text = pais.nombre
        }

        return view
    }
}

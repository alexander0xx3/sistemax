package com.example.sistemax.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sistemax.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val editTextNombre = root.findViewById<EditText>(R.id.editTextNombre)
        val btnSaludo = root.findViewById<Button>(R.id.btnSaludo)
        val btnIrAPerfil = root.findViewById<Button>(R.id.btnIrAPerfil)
        val btnBuscarGoogle = root.findViewById<Button>(R.id.btnBuscarGoogle)
        val btnToggleGrafico = root.findViewById<Button>(R.id.btnToggleGrafico)
        val imagenGrafico = root.findViewById<ImageView>(R.id.imagenGrafico)

        // Cargar imagen de gráfico
        imagenGrafico.setImageResource(R.drawable.grafico)

        btnSaludo.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            if (nombre.isNotEmpty()) {
                Toast.makeText(requireContext(), "Bienvenido $nombre", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show()
            }
        }

        btnIrAPerfil.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            if (nombre.isNotEmpty()) {
                // Solución alternativa sin usar Directions
                val bundle = Bundle()
                bundle.putString("nombre", nombre)
                findNavController().navigate(R.id.nav_gallery, bundle)
            } else {
                Toast.makeText(requireContext(), "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show()
            }
        }

        btnBuscarGoogle.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            if (nombre.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=$nombre"))
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show()
            }
        }

        btnToggleGrafico.setOnClickListener {
            if (imagenGrafico.visibility == View.GONE) {
                imagenGrafico.visibility = View.VISIBLE
                btnToggleGrafico.text = "Ocultar Gráfico"
            } else {
                imagenGrafico.visibility = View.GONE
                btnToggleGrafico.text = "Mostrar Gráfico"
            }
        }

        return root
    }
}
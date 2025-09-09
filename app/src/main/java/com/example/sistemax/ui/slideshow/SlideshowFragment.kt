package com.example.sistemax.ui.slideshow

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sistemax.R

class SlideshowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        val circulo = root.findViewById<View>(R.id.circuloAnimado)

        // Crear animación de movimiento horizontal
        val animator = ObjectAnimator.ofFloat(circulo, "translationX", -300f, 300f).apply {
            duration = 2000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
            start()
        }

        return root
    }
}
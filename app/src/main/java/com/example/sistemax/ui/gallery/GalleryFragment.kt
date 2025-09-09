package com.example.sistemax.ui.gallery

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.sistemax.R
import com.example.sistemax.Empleado

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        val textViewNombre = root.findViewById<TextView>(R.id.textViewNombre)
        val textViewCargo = root.findViewById<TextView>(R.id.textViewCargo)
        val textViewEdad = root.findViewById<TextView>(R.id.textViewEdad)
        val btnEnviarReporte = root.findViewById<Button>(R.id.btnEnviarReporte)

        // Obtener el nombre pasado desde HomeFragment
        val nombre = arguments?.getString("nombre") ?: ""

        // Crear un empleado de ejemplo
        val empleado = Empleado(nombre, "Desarrollador", 30)

        // Mostrar información del empleado
        textViewNombre.text = "Nombre: ${empleado.nombre}"
        textViewCargo.text = "Cargo: ${empleado.cargo}"
        textViewEdad.text = "Edad: ${empleado.edad}"

        // Mostrar Toast cuando se carga el perfil
        Toast.makeText(requireContext(), "Perfil cargado: ${empleado.nombre}", Toast.LENGTH_SHORT).show()

        btnEnviarReporte.setOnClickListener {
            showNotification()
        }

        return root
    }

    private fun showNotification() {
        val channelId = "canal_reporte"
        val notificationId = 1

        // Crear canal de notificación (necesario para Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Canal de Reportes",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Canal para notificaciones de reportes"
            }

            val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Construir la notificación
        val notificationBuilder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Reporte enviado")
            .setContentText("El reporte ha sido enviado correctamente.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Mostrar la notificación
        with(NotificationManagerCompat.from(requireContext())) {
            notify(notificationId, notificationBuilder.build())
        }
    }
}
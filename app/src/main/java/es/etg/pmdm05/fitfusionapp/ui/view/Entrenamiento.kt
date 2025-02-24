package es.etg.pmdm05.fitfusionapp.ui.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.etg.pmdm05.fitfusionapp.R
import es.etg.pmdm05.fitfusionapp.databinding.ActivityEntrenamientoBinding
import es.etg.pmdm05.fitfusionapp.databinding.ActivityInicioSesionBinding
import es.etg.pmdm05.fitfusionapp.databinding.FragmentMenusBinding

class Entrenamiento : AppCompatActivity() {

    private lateinit var binding: ActivityEntrenamientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntrenamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)
// Configurar los Spinners
        configurarSpinners()
    }

    private fun configurarSpinners() {
        // Lista de objetivos (por ejemplo, fuerza, resistencia, hipertrofia)
        val objetivos = listOf("Fuerza", "Resistencia", "Hipertrofia")

        // Lista de rutinas (Weider, Push, Full Body)
        val rutinas = listOf("Weider", "Push", "Full Body")

        // Configurar el Spinner para el objetivo de entrenamiento
        val objetivosAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, objetivos)
        objetivosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerObjetivo.adapter = objetivosAdapter

        // Configurar el Spinner para la rutina de entrenamiento
        val rutinasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rutinas)
        rutinasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRutina.adapter = rutinasAdapter

        // Aquí escuchamos el cambio de selección en el Spinner de Objetivos
       /* binding.spinnerObjetivo.setOnItemSelectedListener { _, _, position, _ ->
            val objetivoSeleccionado = objetivos[position]
            // Aquí puedes agregar lógica para manejar lo que sucede cuando el usuario selecciona un objetivo
            // Si tienes más lógica o filtros para los ejercicios, los podrías aplicar aquí
        }

        // Escuchar el cambio de selección en el Spinner de Rutinas
        binding.spinnerRutina.setOnItemSelectedListener { _, _, position, _ ->
            val rutinaSeleccionada = rutinas[position]
            // Aquí puedes manejar lo que sucede cuando se selecciona una rutina
        }*/
    }
}

package es.etg.pmdm05.fitfusionapp.ui.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import es.etg.pmdm05.fitfusionapp.R
import es.etg.pmdm05.fitfusionapp.databinding.ActivityEntrenamientoBinding
import es.etg.pmdm05.fitfusionapp.ui.fragment.ObjetivoFragment

class Entrenamiento : AppCompatActivity() {

    private lateinit var binding: ActivityEntrenamientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntrenamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Entrenamiento"
// Configurar los Spinners
        configurarSpinner()
    }

    private fun configurarSpinner() {

        val opciones = arrayOf( // Opciones para el Spinner (categorías generales)
            "Por Objetivo",
            "Por Rutina",
            "Nivel de Dificultad",
            "En Casa",
            "Al Aire Libre",
            "Duración"
        )

        // Crear el adapter para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOpciones.adapter = adapter

        // Listener para manejar la selección
        binding.spinnerOpciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedOption = parent?.getItemAtPosition(position).toString()
                // Llamar a la función para mostrar el fragmento relacionado con la opción seleccionada
                mostrarFragmento(selectedOption)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Si no se selecciona nada, no se hace nada
            }
        }
    }
    private fun mostrarFragmento(selectedOption: String) {
        val fragment = when (selectedOption) {
            "Por Objetivo" -> ObjetivoFragment()  // Fragment para mostrar los objetivos
            else -> null  // No manejamos otras opciones
        }

        // Comprobamos si el fragmento es no nulo
        if (fragment != null) {
            // Transacción de fragmento: reemplazamos el fragmento actual por el nuevo fragmento
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)  // R.id.fragment_container es el contenedor de tu fragmento
                .commit()
        }

    /*private fun mostrarFragmento(selectedOption: String) {
        val fragment = when (selectedOption) {
            "Por Objetivo" -> ObjetivoFragment()  // Fragment para mostrar los objetivos
           // "Por Rutina" -> RutinaFragment()  // Fragment para mostrar las rutinas
          //  "Nivel de Dificultad" -> DificultadFragment()  // Fragment para mostrar los niveles de dificultad
           // "En Casa" -> EnCasaFragment()  // Fragment para mostrar ejercicios en casa
           // "Al Aire Libre" -> AireLibreFragment()  // Fragment para mostrar ejercicios al aire libre
           // "Duración" -> DuracionFragment()  // Fragment para mostrar la duración
           // else -> DefaultFragment()  // Fragment por defecto si no se encuentra la opción
        }

        // Transacción de fragmento: reemplazamos el fragmento actual por el nuevo fragmento
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)  // R.id.fragment_container es el contenedor de tu fragmento
            .commit()*/
    }


}




package es.etg.pmdm05.fitfusionapp.ui.view

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import es.etg.pmdm05.fitfusionapp.R
import es.etg.pmdm05.fitfusionapp.databinding.ActivityEntrenamientoBinding
import es.etg.pmdm05.fitfusionapp.ui.fragment.ObjetivoFragment
import es.etg.pmdm05.fitfusionapp.ui.viewmodel.EntrenamientoViewModel

class Entrenamiento : AppCompatActivity() {

    private lateinit var binding: ActivityEntrenamientoBinding
    private lateinit var viewModel: EntrenamientoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntrenamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EntrenamientoViewModel::class.java)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Habilitar la flecha
        supportActionBar?.title = "Entrenamiento"
// Configurar los Spinners
        configurarSpinner()

        // Observar el LiveData del fragmento
        viewModel.fragmento.observe(this, Observer { fragment ->
            if (fragment != null) {
                // Mostrar el fragmento cuando el ViewModel lo indique
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        })

        // Observar el LiveData del mensaje
        viewModel.mensajeNoDisponible.observe(this, Observer { mensaje ->
            if (mensaje.isNotEmpty()) {
                mostrarMensajeNoDisponible(mensaje)
            } else {
                // Si el mensaje está vacío, eliminamos cualquier vista residual
                val layout = findViewById<FrameLayout>(R.id.fragment_container)
                layout.removeAllViews()  // Limpiar el contenedor de cualquier vista residual
            }
        })
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
                viewModel.manejarSeleccionOpcion(selectedOption)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Si no se selecciona nada, no se hace nada
            }
        }
    }
    // Función para mostrar el mensaje "Aún no disponible"
    private fun mostrarMensajeNoDisponible(mensaje: String) {
        val textView = TextView(this)
        textView.text = mensaje
        textView.textSize = 18f
        textView.setTextColor(ContextCompat.getColor(this, R.color.black))
        textView.gravity = Gravity.CENTER

        // Limpiamos cualquier fragmento o vista anterior
        val layout = findViewById<FrameLayout>(R.id.fragment_container)
        layout.removeAllViews()  // Limpiar el contenedor
        layout.addView(textView)  // Agregar el TextView con el mensaje
    }

    // Manejo de la flecha de regreso
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()  // Para manejar el retroceso
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    }







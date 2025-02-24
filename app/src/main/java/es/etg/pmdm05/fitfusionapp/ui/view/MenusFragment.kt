package es.etg.pmdm05.fitfusionapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.etg.pmdm05.fitfusionapp.R
import es.etg.pmdm05.fitfusionapp.databinding.FragmentMenusBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MenusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenusFragment : Fragment() {

    private lateinit var binding: FragmentMenusBinding
    private lateinit var spinnerMes: Spinner
    private lateinit var linearSemanas: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenusBinding.inflate(inflater, container, false)

        spinnerMes = binding.spnMes
        linearSemanas = binding.linearSemanas

        // Asignar el adaptador al Spinner
        spinnerMes.adapter = getMesesAdapter()

        // Listener para cuando el usuario elija un mes
        spinnerMes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Limpiar los botones de semanas previos
                linearSemanas.removeAllViews()

                // Mostrar botones de las 4 semanas según el mes elegido
                mostrarBotonesSemanas(position)
            }
            override fun onNothingSelected(parentView: AdapterView<*>) {  // No hacer nada si no se selecciona un mes
            }
        }

        return binding.root
    }

    // Mostrar los botones de las semanas dependiendo del mes seleccionado
    private fun mostrarBotonesSemanas(mes: Int) {
        // Puedes ajustar las semanas según el mes
        val semanas = resources.getStringArray(R.array.lista_semanas)  // Obtener la lista de semanas desde strings.xml

        for (i in semanas.indices) {
            // Crear un botón para cada semana
            val botonSemana = Button(context)
            botonSemana.text = semanas[i]
            botonSemana.setOnClickListener {
                // Acciones que quieres hacer cuando se presiona un botón
                // Como por ejemplo mostrar el menú de esa semana
                Toast.makeText(context, "Menú de la ${semanas[i]} seleccionado", Toast.LENGTH_SHORT)
                    .show()
            }

            // Agregar el botón al contenedor LinearLayout
            linearSemanas.addView(botonSemana)
        }
    }

    // Método para obtener el adaptador de meses desde el archivo XML
    fun getMesesAdapter(): ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lista_meses,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }
}
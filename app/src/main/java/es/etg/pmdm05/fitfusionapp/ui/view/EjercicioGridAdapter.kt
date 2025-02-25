package es.etg.pmdm05.fitfusionapp.ui.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import es.etg.pmdm05.fitfusionapp.R

import es.etg.pmdm05.fitfusionapp.databinding.ItemEjercicioBinding
import es.etg.pmdm05.fitfusionapp.domain.model.Deporte

class EjercicioGridAdapter(
    private val context: Context,
    private val listaDeportes: List<Deporte>
) : BaseAdapter() {

    override fun getCount(): Int {
        return listaDeportes.size
    }

    override fun getItem(position: Int): Any {
        return listaDeportes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Usa DataBinding para inflar el layout
        val binding: ItemEjercicioBinding
        val view: View

        if (convertView == null) {
            binding = ItemEjercicioBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
        } else {
            binding = ItemEjercicioBinding.bind(convertView)
            view = convertView
        }

        // Asignar el objeto de datos (ItemViewModel) al binding
        val deporte = listaDeportes[position]
        binding.tvTitulo.text =deporte.nombre
        binding.tvDescripcion.text = deporte.descripcion

        println("Item: ${deporte.nombre}")

        return view  // Regresa la vista vinculada
    }
}



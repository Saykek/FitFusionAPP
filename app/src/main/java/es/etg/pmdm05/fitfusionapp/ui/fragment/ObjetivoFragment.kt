package es.etg.pmdm05.fitfusionapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import es.etg.pmdm05.fitfusionapp.data.Repository.DeporteRepository
import es.etg.pmdm05.fitfusionapp.data.gimansiodb
import es.etg.pmdm05.fitfusionapp.databinding.FragmentObjetivoBinding
import es.etg.pmdm05.fitfusionapp.domain.model.Deporte
import es.etg.pmdm05.fitfusionapp.domain.usecase.ObtenerDeporteUseCase
import es.etg.pmdm05.fitfusionapp.ui.view.EjercicioGridAdapter
import es.etg.pmdm05.fitfusionapp.ui.viewmodel.DeporteViewModel


class ObjetivoFragment : Fragment() {

    private lateinit var binding: FragmentObjetivoBinding
    private lateinit var ejercicioAdapter: EjercicioGridAdapter
    private lateinit var deporteViewModel:DeporteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentObjetivoBinding.inflate(inflater, container, false)
        println("onCreateView ejecutado")

// Inicializamos el ViewModel
        val deporteDao = Room.databaseBuilder(requireContext(), gimansiodb::class.java, "gimnasio.db").build().deporteDao()
        val deporteRepository = DeporteRepository(requireContext())
        val obtenerDeporteUseCase = ObtenerDeporteUseCase(deporteRepository)
        deporteViewModel = DeporteViewModel(obtenerDeporteUseCase)

        deporteViewModel.deporteModel.observe(viewLifecycleOwner, { deportes ->
            actualizarGridView(deportes)
        })

        cargarDeportes() // Cargar los deportes desde la base de datos en el lifecycleScope
        print("despues de cargar deportes")

        return binding.root
    }

    public fun cargarDeportes() {

        deporteViewModel.cargarDeportes()

    }
    fun actualizarGridView(deportes: List<Deporte>){
        ejercicioAdapter = EjercicioGridAdapter(requireContext(), deportes)
        binding.gridViewEjercicios.adapter = ejercicioAdapter

}
}


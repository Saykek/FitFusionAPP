package es.etg.pmdm05.fitfusionapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.etg.pmdm05.fitfusionapp.domain.model.Deporte
import es.etg.pmdm05.fitfusionapp.domain.usecase.ObtenerDeporteUseCase
import kotlinx.coroutines.launch

class DeporteViewModel (private val obtenerDeporteUseCase : ObtenerDeporteUseCase): ViewModel(){

    val deporteModel = MutableLiveData<List<Deporte>>()

// CARGAMOS DEPORTES

    fun cargarDeportes() {
        viewModelScope.launch {
            val deportes = obtenerDeporteUseCase.invoke() //realiza llamada a usecase
            deporteModel.postValue(deportes)
        }
    }
    }

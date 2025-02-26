package es.etg.pmdm05.fitfusionapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.etg.pmdm05.fitfusionapp.data.REST.TiempoResponse
import es.etg.pmdm05.fitfusionapp.domain.usecase.ObtenerTiempoUseCase
import kotlinx.coroutines.launch


class TiempoViewModel(private val obtenerTiempoUseCase: ObtenerTiempoUseCase) : ViewModel() {

    private val _tiempoResponse = MutableLiveData<TiempoResponse?>() // Ahora puede ser null
    val tiempoResponse: LiveData<TiempoResponse?> get() = _tiempoResponse

    // Método que invoca al UseCase para obtener los datos
    fun consultarTiempo(provincia: String) {
        viewModelScope.launch {
            val tiempo = obtenerTiempoUseCase.obtenerTiempo(provincia)
            _tiempoResponse.value = tiempo // Esto puede ser null si no se obtiene una respuesta
        }
    }
}



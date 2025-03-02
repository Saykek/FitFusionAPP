package es.etg.pmdm05.fitfusionapp.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.etg.pmdm05.fitfusionapp.ui.fragment.ObjetivoFragment

class EntrenamientoViewModel : ViewModel(){

    private val _fragmento = MutableLiveData<Fragment?>()
    val fragmento: LiveData<Fragment?> get() = _fragmento

    private val _mensajeNoDisponible = MutableLiveData<String>()
    val mensajeNoDisponible: LiveData<String> get() = _mensajeNoDisponible

    fun manejarSeleccionOpcion(selectedOption: String) {
        when (selectedOption) {
            "Por Objetivo" -> {
                _fragmento.value = ObjetivoFragment()  // Mostrar el fragmento de Objetivos
                _mensajeNoDisponible.value = ""  // Limpiar cualquier mensaje
            }
            "Por Rutina" -> {
                _fragmento.value = null
                _mensajeNoDisponible.value = "Aún no disponible"
            }"Nivel de Dificultad" -> {
            _fragmento.value = null
            _mensajeNoDisponible.value = "Aún no disponible"
            }"En Casa" -> {
            _fragmento.value = null
            _mensajeNoDisponible.value = "Aún no disponible"
            }"Al Aire Libre" -> {
            _fragmento.value = null
            _mensajeNoDisponible.value = "Aún no disponible"
            }"Duración" -> {
            _fragmento.value = null
            _mensajeNoDisponible.value = "Aún no disponible"
            }
            else -> {
                _fragmento.value = null  // No fragmento disponible
                _mensajeNoDisponible.value = "Opción no disponible"
            }
        }
    }
}

package es.etg.pmdm05.fitfusionapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.etg.pmdm05.fitfusionapp.domain.usecase.ObtenerPdfUseCase
import kotlinx.coroutines.launch
import java.io.File

class PdfViewModel(private val obtenerPdfUseCase: ObtenerPdfUseCase) : ViewModel() {

    private val _archivoPdf = MutableLiveData<File?>()
    val archivoPdf: LiveData<File?> = _archivoPdf


    fun obtenerPdf(context: Context, nombreArchivo: String) {
        viewModelScope.launch {
            try {
                val archivo = obtenerPdfUseCase.obtenerPdf(context, nombreArchivo)
                _archivoPdf.postValue(archivo)
            } catch (e: Exception) {
                e.printStackTrace()
                _archivoPdf.postValue(null) // En caso de error
            }
        }
    }
}
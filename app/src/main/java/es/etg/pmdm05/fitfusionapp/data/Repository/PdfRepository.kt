package es.etg.pmdm05.fitfusionapp.data.Repository

import android.content.Context
import android.net.Uri
import es.etg.pmdm05.fitfusionapp.data.model.PdfModel

class PdfRepository {

    fun obtenerDesdeAssets(context: Context, nombreArchivo: String ): PdfModel {

        val ruta = Uri.parse("file:///android_asset/$nombreArchivo")
        return PdfModel(nombreArchivo, ruta)
    }
}
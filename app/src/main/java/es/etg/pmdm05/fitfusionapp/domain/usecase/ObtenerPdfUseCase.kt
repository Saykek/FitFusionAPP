package es.etg.pmdm05.fitfusionapp.domain.usecase

import android.content.Context
import java.io.File
import java.io.IOException


class ObtenerPdfUseCase {


    fun obtenerPdf(context: Context, nombreArchivo: String): File? {
        val assetManager = context.assets
        val file = File(context.cacheDir, nombreArchivo)

        try {
            val inputStream = assetManager.open(nombreArchivo)
            // Guardar el archivo en el almacenamiento temporal (cacheDir)
            inputStream.use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return file
    }
}

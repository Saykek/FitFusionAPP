package es.etg.pmdm05.fitfusionapp.data.Repository

import es.etg.pmdm05.fitfusionapp.data.REST.TiempoApi
import es.etg.pmdm05.fitfusionapp.data.REST.TiempoResponse
import retrofit2.Response

class TiempoRepository ( private val tiempoApi: TiempoApi) {

    suspend fun obtenerTiempo(provincia: String): TiempoResponse? {
        val response = tiempoApi.getTiempo(provincia)
        if (response.isSuccessful) {
            return response.body()

        }
        return null
    }
}
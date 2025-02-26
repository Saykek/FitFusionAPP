package es.etg.pmdm05.fitfusionapp.domain.usecase

import es.etg.pmdm05.fitfusionapp.data.REST.TiempoResponse
import es.etg.pmdm05.fitfusionapp.data.Repository.TiempoRepository


class ObtenerTiempoUseCase(private val tiempoRepository: TiempoRepository) {

    // Ejecutamos la llamada al repositorio para obtener los datos
    suspend fun obtenerTiempo(provincia: String): TiempoResponse? {
        return tiempoRepository.obtenerTiempo(provincia)
    }
}

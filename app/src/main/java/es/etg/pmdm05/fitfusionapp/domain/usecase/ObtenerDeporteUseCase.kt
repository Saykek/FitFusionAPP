package es.etg.pmdm05.fitfusionapp.domain.usecase

import es.etg.pmdm05.fitfusionapp.data.Repository.DeporteRepository
import es.etg.pmdm05.fitfusionapp.domain.model.Deporte

class ObtenerDeporteUseCase (private val deporteRepository: DeporteRepository){

    suspend operator fun invoke(): List<Deporte>{

        return deporteRepository.obtenerDeportes()
    }

}
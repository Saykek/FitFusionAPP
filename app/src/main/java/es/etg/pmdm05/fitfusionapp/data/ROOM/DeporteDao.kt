package es.etg.pmdm05.fitfusionapp.data.ROOM

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DeporteDao {

    @Insert
    suspend fun insertDeporte(deporte: DeporteEntity) : Long

    @Insert
    suspend fun insertEjercicio(ejercicio: EjercicioEntity)

    // Obtengo todos los ejercicios de un deporte específico
    @Query("SELECT * FROM ejercicios WHERE deporteId = :deporteId")
    suspend fun obtenerEjerciciosPorDeporte(deporteId: Int): List<EjercicioEntity>

    // Obtener todos los deportes
    @Query("SELECT * FROM deporte")
    suspend fun getAllDeportes(): List<DeporteEntity>
}

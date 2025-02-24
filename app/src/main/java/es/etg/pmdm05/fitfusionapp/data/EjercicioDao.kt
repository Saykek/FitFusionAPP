package es.etg.pmdm05.fitfusionapp.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete

@Dao
interface EjercicioDao {

    @Insert
    suspend fun insertEjercicio(ejercicio: EjercicioEntity)

    // Obtengo todos los ejercicios
    @Query("SELECT * FROM ejercicios")
    suspend fun getAllEjercicios(): List<EjercicioEntity>

    // Obtengo ejercicios de un deporte específico
    @Query("SELECT * FROM ejercicios WHERE deporteId = :deporteId")
    suspend fun ObtenerEjerciciosPorDeporte(deporteId: Int): List<EjercicioEntity>

    @Delete
    suspend fun borratEjercicio(ejercicio: EjercicioEntity)
}

package es.etg.pmdm05.fitfusionapp.data.ROOM

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "ejercicios")
data class EjercicioEntity(

    @PrimaryKey(autoGenerate = true)
    val idEjercicio: Int = 0,
    val nombre: String,
    val descripcion: String,
    val intensidad : String,
    val deporteId: Int
)

package es.etg.pmdm05.fitfusionapp.data.ROOM

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "deporte")
data class DeporteEntity(
    @PrimaryKey (autoGenerate = true)
    val idDeporte: Int = 0,
    val nombre: String,
    val descripcion: String,

)

package es.etg.pmdm05.fitfusionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "deporte")
data class DeporteEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val nombre: String,

)

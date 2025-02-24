package es.etg.pmdm05.fitfusionapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DeporteEntity::class, EjercicioEntity::class], version = 1)
abstract class gimansiodb:RoomDatabase() {

    abstract fun deporteDao():DeporteDao
    abstract fun ejercicioDao() : EjercicioDao


}
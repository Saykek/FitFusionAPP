package es.etg.pmdm05.fitfusionapp.data.Repository

import android.content.Context
import es.etg.pmdm05.fitfusionapp.data.ROOM.DeporteDao
import es.etg.pmdm05.fitfusionapp.data.ROOM.EjercicioDao
import es.etg.pmdm05.fitfusionapp.data.gimansiodb
import es.etg.pmdm05.fitfusionapp.domain.model.Deporte
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DeporteRepository(context: Context) {

    private val deporteDao: DeporteDao
    private val ejercicioDao: EjercicioDao

    init {
        // Obtener la base de datos
        val db = gimansiodb.getDatabase(context)
        deporteDao = db.deporteDao()
        ejercicioDao = db.ejercicioDao()

        // Llamar al método para cargar los datos iniciales si la base de datos está vacía
        cargarDatosBBDD(db)
    }

    // Llamamos a cargar los datos si la base de datos está vacía
    private fun cargarDatosBBDD(db: gimansiodb) {
        // Si no hay deportes en la base de datos, los insertamos
        CoroutineScope(Dispatchers.IO).launch {


            db.cargarDatosBBDD(db) // Llamada al método de carga de datos de la base de datos
        }
    }



    suspend fun obtenerDeportes(): List<Deporte> {  // Función suspendida para obtener la lista de deportes
        // Llamamos al DAO para obtener los deportes de la base de datos
        val deportesDb = deporteDao.getAllDeportes()  // Datos de la base de datos

        // Transformamos los datos de la base de datos al modelo de dominio
        return deportesDb.map { deporteEntity ->
            Deporte(
                id = deporteEntity.idDeporte,
                nombre = deporteEntity.nombre,
                descripcion = deporteEntity.descripcion
            )
        }
    }
}
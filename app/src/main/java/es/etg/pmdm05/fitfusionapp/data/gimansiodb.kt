package es.etg.pmdm05.fitfusionapp.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.etg.pmdm05.fitfusionapp.data.ROOM.DeporteDao
import es.etg.pmdm05.fitfusionapp.data.ROOM.DeporteEntity
import es.etg.pmdm05.fitfusionapp.data.ROOM.EjercicioDao
import es.etg.pmdm05.fitfusionapp.data.ROOM.EjercicioEntity

@Database(entities = [DeporteEntity::class, EjercicioEntity::class], version = 1)
abstract class gimansiodb:RoomDatabase() {

    abstract fun deporteDao(): DeporteDao
    abstract fun ejercicioDao() : EjercicioDao

    companion object {
        // Definir la instancia única de la base de datos
        @Volatile
        private var INSTANCE: gimansiodb? = null

        // Método para obtener la base de datos
        fun getDatabase(context: Context): gimansiodb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    gimansiodb::class.java,
                    "gimansiodb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    // Llenar la base de datos con datos predeterminados
    suspend fun cargarDatosBBDD(db: gimansiodb) {
        val deporteDao = db.deporteDao()
        val ejercicioDao = db.ejercicioDao()

        // Verificamos si ya existen datos en la tabla "deporte"
        if (deporteDao.getAllDeportes().isEmpty()) {
            // Insertamos los deportes y obtenemos su id
            val fuerzaId = deporteDao.insertDeporte(
                DeporteEntity(
                    0,
                    "Fuerza",
                    "Entrenamiento con pesas para fortalecer músculos")
            ).toInt()

            val resistenciaId = deporteDao.insertDeporte(
                DeporteEntity(
                    0,
                    "Resistencia",
                    "Ejercicios para mejorar el sistema cardiovascular")
            ).toInt()


            val hipertrofiaId = deporteDao.insertDeporte(
                DeporteEntity(
                    0,
                    "Hipertrofia",
                    "Entrenamiento para el crecimiento muscular")
            ).toInt()


            val cardioId = deporteDao.insertDeporte(
                DeporteEntity(
                    0,
                    "Cardio",
                    "Ejercicios de alta intensidad para quemar calorías")
            ).toInt()

            val  flexibilidadId = deporteDao.insertDeporte(
                DeporteEntity(
                    0,
                    "Flexibilidad",
                    "Ejercicios  para mejorar la flexibilidad")
                ).toInt()

            Log.d("INSERT", "Cardio ID: $cardioId, Flexibilidad ID: $flexibilidadId")





            // Insertamos los ejercicios asociados a cada deporte
            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Sentadillas",
                    "Ejercicio básico de piernas",
                    "baja",
                    fuerzaId
                )
            )
            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Flexiones",
                    "Ejercicio de fuerza para el torso",
                    "baja",
                    fuerzaId
                )
            )
            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Levantamiento de pesas",
                    "Ejercicio de fuerza",
                    "alta",
                    fuerzaId
                )
            )
            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Press de banca",
                    "Ejercicio para el pecho",
                    "alta",
                    fuerzaId
                )
            )

            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Correr",
                    "Ejercicio cardiovascular",
                    "media",
                    resistenciaId
                )
            )
            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Saltar la cuerda",
                    "Ejercicio cardiovascular",
                    "media",
                    resistenciaId
                )
            )

            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Burpees",
                    "Ejercicio cardiovascular y de fuerza",
                    "alta",
                    cardioId
                )
            )
            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Escaladores",
                    "Ejercicio cardiovascular de alta intensidad",
                    "alta",
                    cardioId
                )
            )

            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Pesas en máquina",
                    "Ejercicio para el desarrollo muscular",
                    "alta",
                    hipertrofiaId
                )
            )
            ejercicioDao.insertEjercicio(
                EjercicioEntity(
                    0,
                    "Flexión de bíceps",
                    "Ejercicio para fortalecer los brazos",
                    "media",
                    hipertrofiaId
                )
            )
        }
    }
}
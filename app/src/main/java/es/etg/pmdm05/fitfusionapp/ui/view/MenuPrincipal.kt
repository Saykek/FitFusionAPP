package es.etg.pmdm05.fitfusionapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.etg.pmdm05.fitfusionapp.R
import es.etg.pmdm05.fitfusionapp.data.REST.TiempoApi
import es.etg.pmdm05.fitfusionapp.data.Repository.TiempoRepository
import es.etg.pmdm05.fitfusionapp.databinding.ActivityMenuPrincipalBinding
import es.etg.pmdm05.fitfusionapp.domain.usecase.ObtenerTiempoUseCase
import es.etg.pmdm05.fitfusionapp.ui.viewmodel.TiempoViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuPrincipal : AppCompatActivity() {

    companion object {
        const val urlTiempo = "https://www.el-tiempo.net/api/json/v2/provincias/"
    }

    private lateinit var binding: ActivityMenuPrincipalBinding

    // SERVICIOS REST
    private val tiempoViewModel: TiempoViewModel by lazy {
        val tiempoApi = Retrofit.Builder()
            .baseUrl(urlTiempo)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TiempoApi::class.java)

        val tiempoRepository = TiempoRepository(tiempoApi)  // Crear el Repository
        val obtenerTiempoUseCase = ObtenerTiempoUseCase(tiempoRepository)  // Crear el UseCase

        TiempoViewModel(obtenerTiempoUseCase)  // Crear el ViewModel pasando el UseCase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TOOLBAR
        val frase_menu = getString(R.string.frase_menu)
        binding.toolbar.setTitle("$frase_menu")
        setSupportActionBar(binding.toolbar)

        // Observamos el LiveData para actualizar la UI cuando los datos cambien
        tiempoViewModel.tiempoResponse.observe(this, { tiempoResponse ->
            if (tiempoResponse != null) {
                binding.txtTitle.text = tiempoResponse.titulo
                binding.txtHoy.text = tiempoResponse.today.toString()
            } else {
                Toast.makeText(this, "No se pudo obtener la predicción", Toast.LENGTH_LONG).show()
            }
        })

        // PERSISTENCIA DE PREFERENCIAS
        val nombreIngresado = intent.getStringExtra("nombreUsuario") // recupero el nombre
        binding.tvNombreUsuario.text = nombreIngresado

        // BOTONES
        binding.btnNutricion.setOnClickListener {
            val intent: Intent = Intent(this, Nutricion::class.java)
            startActivity(intent)
        }
        binding.btnEntrenamiento.setOnClickListener {
            val intent: Intent = Intent(this, Entrenamiento::class.java)
            startActivity(intent)
        }
    }

    // Inflar el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)  // Inflar el menú que creaste
        return true
    }

    // Manejar los ítems del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.obtener_tiempo -> {
                // Aquí es donde conectas con el servicio REST
                val provincia = "28"
                tiempoViewModel.consultarTiempo(provincia)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


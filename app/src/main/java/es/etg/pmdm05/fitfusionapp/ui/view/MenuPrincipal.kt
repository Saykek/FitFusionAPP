package es.etg.pmdm05.fitfusionapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.etg.pmdm05.fitfusionapp.R
import es.etg.pmdm05.fitfusionapp.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {
    private lateinit var binding: ActivityMenuPrincipalBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

// TOOLBAR
        val nombreApp = getString(R.string.app_name)
        binding.toolbar.setTitle("$nombreApp ")
        setSupportActionBar(binding.toolbar)


// PERSISTENCIA DE PREFERENCIAS
        val nombreIngresado = intent.getStringExtra("nombreUsuario") // recupero el nombre
        binding.tvNombreUsuario.text=nombreIngresado


// BOTONES
        binding.btnNutricion.setOnClickListener {
            val intent: Intent = Intent(this, Nutricion::class.java)
            startActivity(intent)
        }
        binding.btnEntrenamiento.setOnClickListener {
            val intent:Intent = Intent(this, Entrenamiento::class.java)
            startActivity(intent)
        }
        }
    }
package es.etg.pmdm05.fitfusionapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.etg.pmdm05.fitfusionapp.databinding.ActivityMainBinding
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
        }
    }

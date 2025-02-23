package es.etg.pmdm05.fitfusionapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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


// PERSISTENCIA DE PREFERENCIAS
        val nombreIngresado = intent.getStringExtra("nombreUsuario") // recupero el nombre
        binding.tvNombreUsuario.text=nombreIngresado
        }
    }

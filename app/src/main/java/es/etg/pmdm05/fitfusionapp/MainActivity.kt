package es.etg.pmdm05.fitfusionapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.etg.pmdm05.fitfusionapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    binding.btnInicioSesion.setOnClickListener {

        val intent = Intent(this, InicioSesion::class.java)
        startActivity(intent)

    }

    }
}
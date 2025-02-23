package es.etg.pmdm05.fitfusionapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.etg.pmdm05.fitfusionapp.databinding.ActivityMainBinding
import es.etg.pmdm05.fitfusionapp.databinding.ActivityNutricionBinding

class Nutricion : AppCompatActivity() {

    private lateinit var binding: ActivityNutricionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutricionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
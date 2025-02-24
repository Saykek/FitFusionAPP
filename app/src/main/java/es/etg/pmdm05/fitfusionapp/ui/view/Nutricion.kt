package es.etg.pmdm05.fitfusionapp.ui.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.etg.pmdm05.fitfusionapp.databinding.ActivityNutricionBinding

class Nutricion : AppCompatActivity() {

    private lateinit var binding: ActivityNutricionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutricionBinding.inflate(layoutInflater)
        setContentView(binding.root)


/*// TOOLBAR
        val nombreApp = getString(R.string.app_name)
        binding.toolbar.setTitle("$nombreApp ")
        setSupportActionBar(binding.toolbar)*/

//para que se oculte la actividad
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE) // cuando el fragmento se cierra,se libera actividad:

// BOTONES

    binding.btnMenus.setOnClickListener {

        cargarFragmento(MenusFragment())
    }
    /*binding.btnRecetario.setOnClickListener {
        cargarFragmento(RecetarioFragment())
    }
    binding.btnListaCompra.setOnClickListener {
        cargarFragmento(ListaCompraFragment())
    }*/
    }

    private fun cargarFragmento(fragment: Fragment){
        val transaccion = supportFragmentManager.beginTransaction() //iniciamos nueva transaccion
        transaccion.replace(binding.fragmentContainer.id, fragment)
        transaccion.commit()
    }
}
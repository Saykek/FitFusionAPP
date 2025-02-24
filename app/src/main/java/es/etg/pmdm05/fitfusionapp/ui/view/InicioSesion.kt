package es.etg.pmdm05.fitfusionapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.etg.pmdm05.fitfusionapp.databinding.ActivityInicioSesionBinding

class InicioSesion : AppCompatActivity() {



    private lateinit var binding: ActivityInicioSesionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)


// BOTON ENTRAR CON PERSISTENCIA DE PREFERENCIAS
        binding.Entrar.setOnClickListener { view ->
                val nombreIngresado = binding.txtInputUser.text.toString()      // Obtengo el nombre ingresado y lo guardo en una variable
                guardar(nombreIngresado)   // Guardar el nombre en las SharedPreferences
                val nombreGuardado = leer()     // Leer el nombre guardado en SharedPreferences
            if (!nombreGuardado.isNullOrEmpty()) {     // Verificar si el nombre no es null y no está vacío
                binding.txtInputUser.setText(nombreGuardado)    // Si hay un nombre guardado, ponerlo en el EditText

                // Cambio de pantalla
                val intent = Intent(this, MenuPrincipal::class.java)
                intent.putExtra("nombreUsuario", nombreIngresado)
                startActivity(intent)
            }else{
                val toast =
                    Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_LONG)
                    toast.show()
                }
           }
        }



// PERSISTENCIA DE PREFERENCIAS
    private fun guardar (nombre: String?) {
        val nameUser = binding.txtInputUser.text.toString()

        val sharedPref = getPreferences(MODE_PRIVATE) // Accede a las SharedPreferences en modo privado
        val editor = sharedPref.edit() // Edita las preferencias
        editor.putString("nombreUsuario", nameUser) // Guarda el nombre con la clave "nombreUsuario"
        editor.apply() // Aplica los cambios de forma asíncrona
    }
    // Función para leer el nombre de SharedPreferences
    private fun leer (): String? {
        val sharedPref = getPreferences(MODE_PRIVATE) // Accede a las SharedPreferences en modo privado
        val nombre = sharedPref.getString("nombreUsuario", "") // Recupera el valor con la clave "nombre"
        return nombre
    }
    }
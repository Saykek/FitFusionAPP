package es.etg.pmdm05.fitfusionapp.ui.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import es.etg.pmdm05.fitfusionapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val READ_CONTACTS_REQUEST_CODE = 0
        const val CAMERA_PERMISSION_REQUEST_CODE = 1
        const val MJ_CONCEDER_PERMISOS = "Conceda los permisos en ajustes"
        const val PERMISO_CONCEDIDO = " Permiso a cámara concedido"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        solicitarPermisoCamara() // Solicita permiso para la cámara

        binding.btnInicioSesion.setOnClickListener {


            val intent = Intent(this, InicioSesion::class.java)
            startActivity(intent)
        }
    }


// PERMISOS
    private fun solicitarPermisoCamara() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            pedirPermisoCamara()
        } else {
            // Permiso ya otorgado
            Toast.makeText(this, PERMISO_CONCEDIDO, Toast.LENGTH_SHORT).show()
        }
    }
    private fun pedirPermisoCamara() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Toast.makeText(this, MJ_CONCEDER_PERMISOS, Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                READ_CONTACTS_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido
                Toast.makeText(this, PERMISO_CONCEDIDO, Toast.LENGTH_SHORT).show()
            } else {
                // Permiso denegado
                Toast.makeText(this, MJ_CONCEDER_PERMISOS, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package es.etg.pmdm05.fitfusionapp.ui.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import es.etg.pmdm05.fitfusionapp.R
import es.etg.pmdm05.fitfusionapp.databinding.FragmentMenusBinding
import es.etg.pmdm05.fitfusionapp.ui.viewmodel.PdfViewModel
import java.io.File
import java.io.IOException


class MenusFragment : Fragment() {

    private lateinit var binding: FragmentMenusBinding
    private lateinit var spinnerMes: Spinner
    private lateinit var linearSemanas: LinearLayout
    private lateinit var pdfImageView: ImageView
    private val pdfViewModel: PdfViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenusBinding.inflate(inflater, container, false)

        spinnerMes = binding.spnMes
        linearSemanas = binding.linearSemanas
        pdfImageView = binding.pdfImageView  // Asignar el ImageView

        // Asignar el adaptador al Spinner
        spinnerMes.adapter = getMesesAdapter()

        // Listener para cuando el usuario elija un mes
        spinnerMes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Limpiar los botones de semanas previos
                linearSemanas.removeAllViews()

                // Mostrar botones de las 4 semanas según el mes elegido
                mostrarBotonesSemanas(position)
            }
            override fun onNothingSelected(parentView: AdapterView<*>) {  // No hacer nada si no se selecciona un mes
            }
        }
        mostrarBotonesSemanas(0)
        return binding.root
    }

    // Mostrar los botones de las semanas dependiendo del mes seleccionado
    private fun mostrarBotonesSemanas(mes: Int) {
        val semanas = resources.getStringArray(R.array.lista_semanas)  // Obtener la lista de semanas desde strings.xml

        for (i in semanas.indices) {
            // Crear un botón para cada semana
            val botonSemana = Button(context)
            botonSemana.text = semanas[i]

            botonSemana.setOnClickListener {

                if(i == 0) {  // Solo la primera semana mostrará el PDF
                    val nombreArchivo = "menu1.pdf"  // El archivo PDF a mostrar
                    mostrarPdf(requireContext(), nombreArchivo, pdfImageView)  // Llamada al método para mostrar el PDF
                } else {
                // Aquí puedes poner un mensaje o realizar una acción cuando se haga clic en las demás semanas
                Toast.makeText(context, "Aún no disponible para ${semanas[i]}", Toast.LENGTH_SHORT).show()
            }
            }

            // Agregar el botón al contenedor LinearLayout
            linearSemanas.addView(botonSemana)
        }
    }

    private fun mostrarPdf(context: Context, nombreArchivo: String, pdfImageView: ImageView) {
        try {
            // Obtener el archivo PDF desde los assets
            val assetManager = context.assets
            val inputStream = assetManager.open(nombreArchivo)

            // Crear un archivo temporal con el contenido del InputStream
            val tempFile = File(context.cacheDir, "temp.pdf")
            val fileOutputStream = tempFile.outputStream()
            inputStream.copyTo(fileOutputStream)
            fileOutputStream.close()

            // Abrir el archivo con ParcelFileDescriptor
            val fileDescriptor = ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY)

            // Crear un PdfRenderer a partir del archivo
            val pdfRenderer = PdfRenderer(fileDescriptor)

            // Mostrar la primera página del PDF
            if (pdfRenderer.pageCount > 0) {
                val page = pdfRenderer.openPage(0)

                // Crear un Bitmap para la página
                val bitmap = Bitmap.createBitmap(
                    page.width, page.height, Bitmap.Config.ARGB_8888
                )

                // Renderizar la página en el Bitmap
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

                // Mostrar el Bitmap en el ImageView
                pdfImageView.setImageBitmap(bitmap)

                page.close()
            }
            pdfRenderer.close()

            // Eliminar el archivo temporal
            tempFile.delete()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Método para obtener el adaptador de meses desde el archivo XML
    fun getMesesAdapter(): ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lista_meses,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }
}
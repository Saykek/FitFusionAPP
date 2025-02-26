package es.etg.pmdm05.fitfusionapp.data.REST

import android.icu.text.CaseMap.Title
import com.google.gson.annotations.SerializedName

data class TiempoResponse(

    @SerializedName("title") var titulo: String,
    var today: Any,
)

package es.etg.pmdm05.fitfusionapp.data.REST

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface TiempoApi {

    @GET
    suspend fun getTiempo(@Url url:String): Response<TiempoResponse>
}
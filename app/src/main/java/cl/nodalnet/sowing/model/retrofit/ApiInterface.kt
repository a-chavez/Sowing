package cl.nodalnet.sowing.model.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("getall")
    fun getDataFromApi(): Call<SowingList> //aca captura el objeto el Json

    @GET ("gettips")
    fun getDataFormTips(): Call<TipsList>
}
package cl.nodalnet.sowing.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object{
        private const val URL_BASE = "https://www.nodalnet.cl/appsowing/api/"

        fun getRetrofitClient() : ApiInterface {
            val mRetrofitClient = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return mRetrofitClient.create(ApiInterface::class.java)
        }
    }
}
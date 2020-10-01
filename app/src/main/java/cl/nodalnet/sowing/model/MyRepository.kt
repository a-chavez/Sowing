package cl.nodalnet.sowing.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cl.nodalnet.sowing.model.retrofit.RetrofitClient
import cl.nodalnet.sowing.model.retrofit.SowingList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository {
    private val service = RetrofitClient.getRetrofitClient()
    val mLiveData : MutableLiveData<SowingList> = MutableLiveData()

    //La vieja confiable XD
    fun getDataFromServer() {
        //Log.d("Arroz", "getDataFromServer")
        val mCall = service.getDataFromApi()
        mCall.enqueue(object : Callback<SowingList>{
            override fun onResponse(call: Call<SowingList>, response: Response<SowingList>) {
                //Log.d("Arroz onResponse", response.code().toString())
                when(response.code()){
                    in 200..299 -> {
                        mLiveData.postValue(response.body())
                        Log.d("Arroz", response.toString())
                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<SowingList>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }

        })
    }

}
package cl.nodalnet.sowing.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.nodalnet.sowing.model.retrofit.RetrofitClient
import cl.nodalnet.sowing.model.retrofit.SowingList
import cl.nodalnet.sowing.model.room.MasterDAO
import cl.nodalnet.sowing.model.room.MasterDAO_Impl
import cl.nodalnet.sowing.model.room.SowingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository (private val mMasterDAO: MasterDAO){
    private val service = RetrofitClient.getRetrofitClient()

    val mLiveData = mMasterDAO.getAllData()

    //La vieja confiable XD
    fun getDataFromServer() {
        //Log.d("Arroz", "getDataFromServer")
        val mCall = service.getDataFromApi()
        mCall.enqueue(object : Callback<SowingList>{
            override fun onResponse(call: Call<SowingList>, response: Response<SowingList>) {
             //Log.d("Arroz onResponse", response.code().toString())
                when(response.code()){

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch{
                       response.body()?.let{
                           //Log.d("Arroz desde API", it.toString())
                           mMasterDAO.insertAllData(it)
                       }

                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<SowingList>, t: Throwable) {
                Log.e("MyRepository", t.message.toString())
            }

        })
    }

    fun getOneSeed(mName:String) : LiveData<SowingItem>{
        return mMasterDAO.getOneSeed(mName)
    }

    fun getSeedForMonth(mMonth:String) : LiveData<SowingItem>{
       return mMasterDAO.getSeedForMonth(mMonth)
    }
}
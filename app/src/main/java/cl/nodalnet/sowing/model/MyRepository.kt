package cl.nodalnet.sowing.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.nodalnet.sowing.model.retrofit.RetrofitClient
import cl.nodalnet.sowing.model.retrofit.SowingList
import cl.nodalnet.sowing.model.retrofit.TipsList
import cl.nodalnet.sowing.model.room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository (private val mMasterDAO: MasterDAO, private val mTipsDAO: TipsDAO){
    private val service = RetrofitClient.getRetrofitClient()

    val mLiveData = mMasterDAO.getAllData()
    val mLiveDataTips = mTipsDAO.getAllTips()
    val mDataSowingItem = mutableListOf<SowingItem>()
    val mDataTipsItem = mutableListOf<TipsItem>()

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

    fun getDataFromApiTips() {
        //Log.d("Arroz", "getDataFromApiTips")
        val mCall = service.getDataFormTips()
        mCall.enqueue(object : Callback<TipsList>{
            override fun onResponse(call: Call<TipsList>, response: Response<TipsList>) {
              //  Log.d("Arroz onResponse", response.code().toString())
                when(response.code()){

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch{
                        response.body()?.let{
                        //    Log.d("Arroz desde TipsAPI", it.toString())
                            mTipsDAO.insertAllTips(it)
                        }

                    }
                    in 300..399 -> Log.d("Arroz ERROR 300", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<TipsList>, t: Throwable) {
                Log.e("Arroz ApiTips", t.message.toString())
            }

        })
    }

    fun getOneSeed(mName:String) : LiveData<SowingItem>{
        return mMasterDAO.getOneSeed(mName)
    }

    fun getSeedFromMonth(mMonth:String, mSowing:List<SowingItem>) : List<SowingItem>{
        for(data in mSowing){
            if (mMonth=="ene" && data.ene=="1") mDataSowingItem.add(data)
            if (mMonth=="feb" && data.feb=="1") mDataSowingItem.add(data)
            if (mMonth=="mar" && data.mar=="1") mDataSowingItem.add(data)
            if (mMonth=="abr" && data.abr=="1") mDataSowingItem.add(data)
            if (mMonth=="may" && data.may=="1") mDataSowingItem.add(data)
            if (mMonth=="jun" && data.jun=="1") mDataSowingItem.add(data)
            if (mMonth=="jul" && data.jul=="1") mDataSowingItem.add(data)
            if (mMonth=="ago" && data.ago=="1") mDataSowingItem.add(data)
            if (mMonth=="sep" && data.sep=="1") mDataSowingItem.add(data)
            if (mMonth=="oct" && data.oct=="1") mDataSowingItem.add(data)
            if (mMonth=="nov" && data.nov=="1") mDataSowingItem.add(data)
            if (mMonth=="dic" && data.dic=="1") mDataSowingItem.add(data)
        }
        return mDataSowingItem

    }
}
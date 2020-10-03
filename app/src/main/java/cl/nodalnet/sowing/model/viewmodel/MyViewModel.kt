package cl.nodalnet.sowing.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cl.nodalnet.sowing.model.MyRepository
import cl.nodalnet.sowing.model.retrofit.SowingList
import cl.nodalnet.sowing.model.room.SowingDB
import cl.nodalnet.sowing.model.room.SowingItem

class MyViewModel (application: Application): AndroidViewModel(application) {

    private val mMyRepository : MyRepository
    val mAllMaster : LiveData<List<SowingItem>>

    init {
        val mMasterDAO = SowingDB.getDataBase(application).getMasterDAO()

        mMyRepository = MyRepository(mMasterDAO)
        mAllMaster = mMyRepository.mLiveData
        mMyRepository.getDataFromServer()
    }

    fun exposeLiveDataFromServer():LiveData<List<SowingItem>>{
        return mMyRepository.mLiveData
    }

    fun getOneSeed(mName:String) : LiveData<SowingItem>{
        return mMyRepository.getOneSeed(mName)
    }

    fun getSeedForMonth(mMonth:String) : LiveData<SowingItem>{
        return mMyRepository.getOneSeed(mMonth)
    }


}
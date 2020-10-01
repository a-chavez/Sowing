package cl.nodalnet.sowing.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cl.nodalnet.sowing.model.MyRepository
import cl.nodalnet.sowing.model.retrofit.SowingList

class MyViewModel : ViewModel() {

    private val repository = MyRepository()

    init {
        repository.getDataFromServer()

    }

    fun exposeLiveDataFromServer():LiveData<SowingList>{
        return repository.mLiveData
    }


}
package cl.nodalnet.sowing.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cl.nodalnet.sowing.model.MyRepository
import cl.nodalnet.sowing.model.retrofit.SowingList
import cl.nodalnet.sowing.model.room.SowingDB
import cl.nodalnet.sowing.model.room.SowingItem
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.util.*

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

    fun getSeedForMonth(mMonth:String) : LiveData<List<SowingItem>>{
        return mMyRepository.getSeedForMonth(mMonth)
    }

    fun getCurrentMonth(): Array<String> {
        val currentMonth: Int = Calendar.getInstance().get(Calendar.MONTH)+1
        var mMonth :String =""
        var fullMonth : String =""
        when (currentMonth) {
            1->  {mMonth= "ene"
                    fullMonth ="enero" }
            2->  {mMonth= "feb"
                    fullMonth ="febrero" }
            3->  {mMonth= "mar"
                    fullMonth ="marzo" }
            4->  {mMonth= "abr"
                    fullMonth ="abril" }
            5->  {mMonth= "may"
                    fullMonth ="mayo" }
            6->  {mMonth= "jun"
                    fullMonth ="junio" }
            7->  {mMonth= "jul"
                    fullMonth ="julio" }
            8->  {mMonth= "ago"
                    fullMonth ="agosto" }
            9->  {mMonth= "sep"
                    fullMonth ="septiembre" }
            10->  {mMonth= "oct"
                    fullMonth ="octubre" }
            11->  {mMonth= "nov"
                    fullMonth ="noviembre" }
            12->  {mMonth= "dic"
                    fullMonth ="diciembre" }
        }
        return arrayOf(mMonth,fullMonth)
    }


}
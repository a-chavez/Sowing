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

    fun getCurrentMonth(): Array<String> {
        val currentMonth: Int = Calendar.getInstance().get(Calendar.MONTH)+1
        var mMonth :String =""
        var fullMonth : String =""
        when (currentMonth) {
            1->  {mMonth= "ene"
                    fullMonth ="Enero" }
            2->  {mMonth= "feb"
                    fullMonth ="Febrero" }
            3->  {mMonth= "mar"
                    fullMonth ="Marzo" }
            4->  {mMonth= "abr"
                    fullMonth ="Abril" }
            5->  {mMonth= "may"
                    fullMonth ="Mayo" }
            6->  {mMonth= "jun"
                    fullMonth ="Junio" }
            7->  {mMonth= "jul"
                    fullMonth ="Julio" }
            8->  {mMonth= "ago"
                    fullMonth ="Agosto" }
            9->  {mMonth= "sep"
                    fullMonth ="Septiembre" }
            10->  {mMonth= "oct"
                    fullMonth ="Octubre" }
            11->  {mMonth= "nov"
                    fullMonth ="Noviembre" }
            12->  {mMonth= "dic"
                    fullMonth ="Diciembre" }
        }
        return arrayOf(mMonth,fullMonth)
    }

    fun getSeedFromMonth(mMonth:String, mSowing:List<SowingItem>) : List<SowingItem>{
        return mMyRepository.getSeedFromMonth(mMonth,mSowing)
    }

}
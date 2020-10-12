package cl.nodalnet.sowing.model.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cl.nodalnet.sowing.model.MyRepository
import cl.nodalnet.sowing.model.retrofit.SowingList
import cl.nodalnet.sowing.model.room.SowingDB
import cl.nodalnet.sowing.model.room.SowingItem
import cl.nodalnet.sowing.model.room.TipsItem
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.util.*

class MyViewModel (application: Application): AndroidViewModel(application) {

    private val mMyRepository : MyRepository
    val mAllMaster : LiveData<List<SowingItem>>
    val mAllTips : LiveData<List<TipsItem>>

    init {
        val mMasterDAO = SowingDB.getDataBase(application).getMasterDAO()
        val mTipsDAO = SowingDB.getDataBase(application).getTipsDAO()

        mMyRepository = MyRepository(mMasterDAO,mTipsDAO)
        mAllMaster = mMyRepository.mLiveData
        mAllTips = mMyRepository.mLiveDataTips
        mMyRepository.getDataFromServer()
        mMyRepository.getDataFromApiTips()
    }

    fun exposeLiveDataFromServer():LiveData<List<SowingItem>>{
        return mMyRepository.mLiveData
    }

    fun exposeLiveDataTips(): LiveData<List<TipsItem>>{
        return mMyRepository.mLiveDataTips
    }

    fun getOneSeed(mName:String) : LiveData<SowingItem>{
        return mMyRepository.getOneSeed(mName)
    }

    fun getMonthTips(mMonth: String) : LiveData<TipsItem>{
        return mMyRepository.getMonthTips(mMonth)
    }

    fun getSeedFromMonth(mMonth:String, mSowing:List<SowingItem>) : List<SowingItem>{
        return mMyRepository.getSeedFromMonth(mMonth,mSowing)
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

    fun getTitleImgFromMonth(mMonth: String): String{
        var imgUrl:String=""
        when(mMonth){
            "ene" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/summer2.png"
            "feb" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/summer3.png"
            "mar" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/fall1.png"
            "abr" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/fall2.png"
            "may" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/fall3.png"
            "jun" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/winter1.png"
            "jul" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/winter2.png"
            "ago" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/winter3.png"
            "sep" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/spring1.png"
            "oct" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/spring2.png"
            "nov" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/spring3.png"
            "dic" -> imgUrl = "https://www.nodalnet.cl/appsowing/images/summer1.png"
            else -> imgUrl= "https://www.nodalnet.cl/appsowing/images/default.png"

        }
        return imgUrl
    }

 }


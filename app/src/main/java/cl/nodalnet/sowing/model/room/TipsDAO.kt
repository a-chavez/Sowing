package cl.nodalnet.sowing.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TipsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllTips(mTipsListDB:List<TipsItem>)

    @Query("SELECT * FROM tips_table")
    fun getAllTips(): LiveData<List<TipsItem>>

    @Query("SELECT * FROM tips_table WHERE month=:mMonth")
    fun getMonthTips(mMonth: String): LiveData<TipsItem>
}
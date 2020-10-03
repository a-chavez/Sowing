package cl.nodalnet.sowing.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MasterDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)  //todo probar con nuevo item
    suspend fun insertAllData(mSowingListDB: List<SowingItem>)

    @Query("SELECT * FROM master_table")
    fun getAllData(): LiveData<List<SowingItem>>

    @Query("SELECT * FROM master_table WHERE name=:mName")
    fun getOneSeed(mName: String): LiveData<SowingItem>

}
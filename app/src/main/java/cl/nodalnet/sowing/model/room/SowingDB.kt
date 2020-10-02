package cl.nodalnet.sowing.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME="sowing_db"

@Database(entities = [SowingItem::class],version = 1)

abstract class SowingDB : RoomDatabase() {

    abstract fun getMasterDAO() : MasterDAO

    companion object {
        @Volatile
        private var INSTANCE: SowingDB? = null

        fun getDataBase(context: Context): SowingDB {
            //Para que no repita la instancia
            val tempInterface = INSTANCE
            if(tempInterface !=null) {
                return tempInterface
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    SowingDB::class.java,
                    DATA_BASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
package cl.nodalnet.sowing.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tips_table")
data class TipsItem(
    @PrimaryKey
    @SerializedName("id")       val id: String,
    @SerializedName("month")    val month: String,
    @SerializedName("tip1")     val tip1: String?,
    @SerializedName("tip2")     val tip2: String?,
    @SerializedName("tip3")     val tip3: String?,
    @SerializedName("tip4")     val tip4: String?,
    @SerializedName("tip5")     val tip5: String?,
    @SerializedName("tip6")     val tip6: String?,
    @SerializedName("tip7")     val tip7: String?,
    @SerializedName("notes")    val notes: String?
)
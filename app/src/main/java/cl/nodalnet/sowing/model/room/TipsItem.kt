package cl.nodalnet.sowing.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tips_table")
data class TipsItem(
    @PrimaryKey
    @SerializedName("id")       val id: String,
    @SerializedName("month")    val ene: String,
    @SerializedName("tip1")     val feb: String?,
    @SerializedName("tip2")     val mar: String?,
    @SerializedName("tip3")     val abr: String?,
    @SerializedName("tip4")     val may: String?,
    @SerializedName("tip5")     val jun: String?,
    @SerializedName("tip6")     val jul: String?,
    @SerializedName("tip7")     val ago: String?,
    @SerializedName("notes")    val sep: String?
)
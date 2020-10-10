package cl.nodalnet.sowing.model.room


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tips_table")
data class TipsItem(
    @PrimaryKey
    @SerializedName("id")     val id: String,
    @SerializedName("ene")    val ene: String?,
    @SerializedName("feb")    val feb: String?,
    @SerializedName("mar")    val mar: String?,
    @SerializedName("abr")    val abr: String?,
    @SerializedName("may")    val may: String?,
    @SerializedName("jun")    val jun: String?,
    @SerializedName("jul")    val jul: String?,
    @SerializedName("ago")    val ago: String?,
    @SerializedName("sep")    val sep: String?,
    @SerializedName("oct")    val oct: String?,
    @SerializedName("nov")    val nov: String?,
    @SerializedName("dic")    val dic: String?
)
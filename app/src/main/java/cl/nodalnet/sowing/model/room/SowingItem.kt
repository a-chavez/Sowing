package cl.nodalnet.sowing.model.room

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "master_table")
data class SowingItem(
    @SerializedName("id")           val id: String,
    @SerializedName("name")         val name: String,
    @SerializedName("url_image")    val urlImage: String,
    @SerializedName("ene")          val ene: String,
    @SerializedName("feb")          val feb: String,
    @SerializedName("mar")          val mar: String,
    @SerializedName("abr")          val abr: String,
    @SerializedName("may")          val may: String,
    @SerializedName("jun")          val jun: String,
    @SerializedName("jul")          val jul: String,
    @SerializedName("ago")          val ago: String,
    @SerializedName("sep")          val sep: String,
    @SerializedName("oct")          val oct: String,
    @SerializedName("nov")          val nov: String,
    @SerializedName("dic")          val dic: String,
    @SerializedName("sun")          val sun: String,
    @SerializedName("sowing ")      val sowing: String,
    @SerializedName("depth")        val depth: String,
    @SerializedName("distance")     val distance: String,
    @SerializedName("harvest")      val harvest: String,
    @SerializedName("wather")       val wather: String,
    @SerializedName("substratum")   val substratum: String,
    @SerializedName("fertilizer")   val fertilizer: String,
    @SerializedName("moon")         val moon: String,
    @SerializedName("weather")      val weather: String,
    @SerializedName("temp")         val temp: String,
    @SerializedName("friends")      val friends: String,
    @SerializedName("nonfriends")   val nonfriends: String,
    @SerializedName("diseases")     val diseases: String,
    @SerializedName("pot")          val pot: String,

)
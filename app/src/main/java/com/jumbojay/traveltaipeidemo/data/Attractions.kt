package com.jumbojay.traveltaipeidemo.data

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jumbojay.traveltaipeidemo.data.converter.GeneralTypeConverter
import com.jumbojay.traveltaipeidemo.data.converter.ImageConverter
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(tableName = "attractions")
@TypeConverters(GeneralTypeConverter::class, ImageConverter::class)
data class Attractions(
    @Expose
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,

    @Expose
    val name: String = "",

    @Expose
    @SerializedName("name_zh")
    val nameZh: String? = null,

    @Expose
    @SerializedName("open_status")
    val openStatus: Int = 0,

    @Expose
    val introduction: String = "",

    @Expose
    @SerializedName("open_time")
    val openTime: String = "",

    @Expose
    val zipcode: Int = 0,

    @Expose
    val distric: String = "",

    @Expose
    val address: String = "",

    @Expose
    val tel: String = "",

    @Expose
    val fax: String = "",

    @Expose
    val email: String = "",

    @Expose
    val months: String = "",

    @Expose
    val nlat: Float = 0f,

    @Expose
    val elong: Float = 0f,

    @Expose
    @SerializedName("official_site")
    val officialSite: String = "",

    @Expose
    val facebook: String = "",

    @Expose
    val ticket: String = "",

    @Expose
    val remind: String = "",

    @Expose
    val staytime: String = "",

    @Expose
    val modified: String = "",

    @Expose
    val url: String = "",

    @Expose
    val category: List<GeneralType> = listOf(),

    @Expose
    val target: List< GeneralType> = listOf(),

    @Expose
    val service: List<GeneralType> = listOf(),

    @Expose
    val images: List<Image> = listOf()

) : Parcelable {

    @Keep
    @Parcelize
    data class GeneralType(
        @Expose
        val id: Int = 0,

        @Expose
        val name: String = ""
    ) : Parcelable

    @Keep
    @Parcelize
    data class Image(
        @Expose
        val src: String = "",

        @Expose
        val subject: String = "",

        @Expose
        val ext: String = ""
    ) : Parcelable
}
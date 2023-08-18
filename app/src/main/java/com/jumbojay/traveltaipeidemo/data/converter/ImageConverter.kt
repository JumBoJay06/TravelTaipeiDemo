package com.jumbojay.traveltaipeidemo.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jumbojay.traveltaipeidemo.data.Attractions.Image

class ImageConverter {

    @TypeConverter
    fun toImageList(value: String): List<Image> {
        val type = object : TypeToken<List<Image>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromImageList(value: List<Image>): String {
        val type = object : TypeToken<List<Image>>() {}.type
        return Gson().toJson(value, type)
    }
}
package com.jumbojay.traveltaipeidemo.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jumbojay.traveltaipeidemo.data.Attractions.GeneralType


class GeneralTypeConverter  {

    @TypeConverter
    fun toGeneralTypeList(value: String): List<GeneralType> {
        val type = object : TypeToken<List<GeneralType>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromGeneralTypeList(value: List<GeneralType>): String {
        val type = object : TypeToken<List<GeneralType>>() {}.type
        return Gson().toJson(value, type)
    }
}
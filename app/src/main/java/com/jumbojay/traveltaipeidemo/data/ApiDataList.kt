package com.jumbojay.traveltaipeidemo.data

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
class ApiDataList<T> {
    @Expose
    val total: Int = -1

    @Expose
    var data: List<T>? = null
}
package com.jumbojay.traveltaipeidemo.api

import com.google.gson.GsonBuilder
import com.jumbojay.traveltaipeidemo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> Retrofit.Builder.buildWithApi(
    readTimeout: Long = 10
): T {

    val builder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor(HttpLogger()).apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS)

    val okHttpClient = builder.build()

    val retrofit = this
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .build()
    return retrofit.create(T::class.java)
}
package com.jumbojay.traveltaipeidemo.api.services

import com.jumbojay.traveltaipeidemo.data.ApiDataList
import com.jumbojay.traveltaipeidemo.data.Attractions
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AttractionsService {

    @GET("{lang}/Attractions/All")
    @Headers("accept: application/json")
    suspend fun getAttractions(
        @Path("lang") language: String,
        @Query("page") page: Int,
    ): ApiDataList<Attractions>
}
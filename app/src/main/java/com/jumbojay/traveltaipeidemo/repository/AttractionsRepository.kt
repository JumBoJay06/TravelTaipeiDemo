package com.jumbojay.traveltaipeidemo.repository

import com.jumbojay.traveltaipeidemo.api.services.AttractionsService
import com.jumbojay.traveltaipeidemo.database.dao.AttractionsDao

class AttractionsRepository(
    private val attractionsService: AttractionsService,
    private val attractionsDao: AttractionsDao
) {

    val attractions = attractionsDao.attractionsLiveData

    suspend fun refreshAttractions(language: String, page: Int) {
        val attractionsDataList = attractionsService.getAttractions(language, page)
        attractionsDao.saveAttractions(attractionsDataList.data?.toList() ?: listOf())
    }
}
package com.jumbojay.traveltaipeidemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jumbojay.traveltaipeidemo.api.services.AttractionsService
import com.jumbojay.traveltaipeidemo.data.Language
import com.jumbojay.traveltaipeidemo.ui.attractionslist.AttractionsPagingSource

class AttractionsRepository(
    private val attractionsService: AttractionsService
) {

    val selectLang = MutableLiveData(Language.ZH_TW)

    val attractionsPagingFlow = Pager(PagingConfig(30)) {
        AttractionsPagingSource(attractionsService, selectLang.value?.code ?: Language.ZH_TW.code)
    }.flow
}
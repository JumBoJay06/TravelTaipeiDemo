package com.jumbojay.traveltaipeidemo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jumbojay.traveltaipeidemo.data.Attractions
import com.jumbojay.traveltaipeidemo.data.Language
import com.jumbojay.traveltaipeidemo.repository.AttractionsRepository

class AttractionViewModel(
    private val attractionsRepository: AttractionsRepository,
    application: Application,
) : AndroidViewModel(application) {

    private val _selectView = MutableLiveData(ViewType.LIST)
    val selectView: LiveData<ViewType>
        get() = _selectView

    val attractionsPagingFlow =
        attractionsRepository.attractionsPagingFlow
            .cachedIn(viewModelScope)

    private val _selectAttractions = MutableLiveData<Attractions?>()
    val selectAttractions: LiveData<Attractions?>
        get() = _selectAttractions

    private val _selectLang = attractionsRepository.selectLang
    val selectLang: LiveData<Language>
        get() = _selectLang

    fun setLang(language: Language) {
        _selectLang.postValue(language)
    }

    fun onClickItem(attractions: Attractions) {
        _selectAttractions.postValue(attractions)
        gotoDetail()
    }

    fun gotoList() {
        _selectAttractions.postValue(null)
        _selectView.postValue(ViewType.LIST)
    }

    fun gotoDetail() {
        _selectView.postValue(ViewType.DETAIL)
    }

}

enum class ViewType {
    LIST, DETAIL
}
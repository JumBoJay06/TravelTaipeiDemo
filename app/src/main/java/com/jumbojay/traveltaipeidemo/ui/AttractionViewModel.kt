package com.jumbojay.traveltaipeidemo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jumbojay.traveltaipeidemo.repository.AttractionsRepository

class AttractionViewModel(
    private val attractionsRepository: AttractionsRepository,
    application: Application,
) : AndroidViewModel(application) {


}
package com.jumbojay.traveltaipeidemo.di

import com.jumbojay.traveltaipeidemo.api.buildWithApi
import com.jumbojay.traveltaipeidemo.api.services.AttractionsService
import com.jumbojay.traveltaipeidemo.repository.AttractionsRepository
import com.jumbojay.traveltaipeidemo.ui.AttractionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { getWebService<AttractionsService>(10L) }
}

val repositoryModule = module {
    single { AttractionsRepository(get()) }
}

val viewModelModule = module {
    single { AttractionViewModel(get(), androidApplication()) }
}

inline fun <reified T> getWebService(
    readTimeout: Long,
): T = Retrofit.Builder()
    .buildWithApi(readTimeout)

val MyModules = listOf(
    apiModule,
    repositoryModule,
    viewModelModule,
)
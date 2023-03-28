package com.jumbojay.traveltaipeidemo.di

import com.jumbojay.traveltaipeidemo.api.buildWithApi
import com.jumbojay.traveltaipeidemo.api.services.AttractionsService
import com.jumbojay.traveltaipeidemo.database.MyRoomDatabase
import com.jumbojay.traveltaipeidemo.repository.AttractionsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { getWebService<AttractionsService>(10L) }
}

val databaseModule = module {
    single { MyRoomDatabase.getDatabase(androidContext()) }
    single { get<MyRoomDatabase>().attractionsDao() }
}

val repositoryModule = module {
    single { AttractionsRepository(get(), get()) }
}

inline fun <reified T> getWebService(
    readTimeout: Long,
): T = Retrofit.Builder()
    .buildWithApi(readTimeout)

val MyModules = listOf(
    apiModule,
    databaseModule,
    repositoryModule,
)
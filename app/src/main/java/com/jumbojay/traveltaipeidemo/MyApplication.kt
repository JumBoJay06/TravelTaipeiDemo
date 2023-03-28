package com.jumbojay.traveltaipeidemo

import android.app.Application
import com.jumbojay.traveltaipeidemo.di.MyModules
import com.jumbojay.traveltaipeidemo.util.KoinLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            KoinLogger()
            androidContext(this@MyApplication)
            modules(MyModules)
        }
    }
}
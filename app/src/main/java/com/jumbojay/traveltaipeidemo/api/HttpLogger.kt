package com.jumbojay.traveltaipeidemo.api

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class HttpLogger : HttpLoggingInterceptor.Logger {
    companion object {
        const val TAG = "HttpLogInfo"
    }
    override fun log(message: String) {
        if (message.indexOf("-->", 0, false) > -1 || message.indexOf("<--", 0, false) > -1) {
            Timber.tag(TAG).i(message)
        } else {
            Timber.tag(TAG).d(message)
        }
    }
}
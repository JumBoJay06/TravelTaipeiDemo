package com.jumbojay.traveltaipeidemo.util

import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import timber.log.Timber

class KoinLogger: Logger() {
    companion object {
        const val TAG = "KOIN"
    }

    override fun display(level: Level, msg: MESSAGE) = when (level) {
        Level.DEBUG -> Timber.tag(TAG).d(msg)
        Level.INFO -> Timber.tag(TAG).i(msg)
        Level.WARNING -> Timber.tag(TAG).w(msg)
        Level.ERROR -> Timber.tag(TAG).e(msg)
        else -> Unit
    }
}
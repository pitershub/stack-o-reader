package com.example.stackoreader

import android.app.Application
import com.example.stackoreader.log.DebugTree
import timber.log.Timber

class StackOReaderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}
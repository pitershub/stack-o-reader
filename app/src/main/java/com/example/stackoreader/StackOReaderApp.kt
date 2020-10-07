package com.example.stackoreader

import android.app.Application
import com.example.stackoreader.di.AppComponent
import com.example.stackoreader.di.DaggerAppComponent
import com.example.stackoreader.log.DebugTree
import timber.log.Timber

class StackOReaderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}
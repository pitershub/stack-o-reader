package com.pikejprod.stackoreader

import android.app.Application
import com.pikejprod.stackoreader.di.AppComponent
import com.pikejprod.stackoreader.di.DaggerAppComponent
import com.pikejprod.stackoreader.log.DebugTree
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
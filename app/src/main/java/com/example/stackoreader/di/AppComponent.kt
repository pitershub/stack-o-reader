package com.example.stackoreader.di

import android.content.Context
import com.example.stackoreader.view.MainActivity
import com.example.stackoreader.view.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        StackApiModule::class,
        StackServiceModule::class
    ])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(searchFragment: SearchFragment)
}
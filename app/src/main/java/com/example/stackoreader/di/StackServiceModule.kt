package com.example.stackoreader.di

import com.example.stackoreader.model.StackApi
import com.example.stackoreader.model.StackService
import com.example.stackoreader.model.StackServiceImpl
import dagger.Module
import dagger.Provides

@Module
class StackServiceModule {
    @Provides
    fun provideStackServiceModule(api: StackApi): StackService {
        return StackServiceImpl(api)
    }
}
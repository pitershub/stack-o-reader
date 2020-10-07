package com.pikejprod.stackoreader.di

import com.pikejprod.stackoreader.model.StackApi
import com.pikejprod.stackoreader.model.StackService
import com.pikejprod.stackoreader.model.StackServiceImpl
import dagger.Module
import dagger.Provides

@Module
class StackServiceModule {
    @Provides
    fun provideStackServiceModule(api: StackApi): StackService {
        return StackServiceImpl(api)
    }
}
package com.pikejprod.stackoreader.di

import com.pikejprod.stackoreader.model.StackApi
import com.pikejprod.stackoreader.model.StackService
import com.pikejprod.stackoreader.model.StackServiceImpl
import com.pikejprod.stackoreader.viewmodel.StackOState
import dagger.Module
import dagger.Provides

@Module
class StackOStateModule {
    @Provides
    fun provideStackOStateModule(): StackOState {
        return StackOState(activity = false)
    }
}
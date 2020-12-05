package com.pikejprod.stackoreader.di

import com.pikejprod.stackoreader.model.StackService
import com.pikejprod.stackoreader.viewmodel.SearchViewModel
import com.pikejprod.stackoreader.viewmodel.StackOState
import dagger.Module
import dagger.Provides

@Module
class SearchViewModelModule {
    @Provides
    fun provideSearchViewModelModule(initState: StackOState, stackService: StackService): SearchViewModel {
        return SearchViewModel(initState, stackService)
    }
}
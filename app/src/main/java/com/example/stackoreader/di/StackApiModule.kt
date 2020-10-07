package com.example.stackoreader.di

import com.example.stackoreader.model.StackApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class StackApiModule {

    private val BASE_URL = "https://api.stackexchange.com/2.2/"

    @Provides
    fun provideStackApiModule(): StackApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(StackApi::class.java)
    }

}
package com.example.stackoreader.model

import com.example.stackoreader.data.SearchResult
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StackService {

    private val BASE_URL = "https://api.stackexchange.com/2.2/"

    private val ORDER = "desc"
    private val SORT = "activity"
    private val SITE = "stackoverflow"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(StackApi::class.java)

    fun search(query: String): Single<SearchResult> {
        return api.getSearchResults(ORDER, SORT, query, SITE)
    }

}
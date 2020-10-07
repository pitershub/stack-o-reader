package com.pikejprod.stackoreader.model

import com.pikejprod.stackoreader.data.SearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StackApi {
    @GET("search")
    fun getSearchResults(
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("intitle") intitle: String,
        @Query("site") site: String): Single<SearchResult>

}
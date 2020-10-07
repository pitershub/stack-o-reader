package com.pikejprod.stackoreader.model

import com.pikejprod.stackoreader.data.SearchResult
import io.reactivex.Single

interface StackService {
    fun search(query: String): Single<SearchResult>
}
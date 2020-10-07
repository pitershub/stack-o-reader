package com.example.stackoreader.model

import com.example.stackoreader.data.SearchResult
import io.reactivex.Single

interface StackService {
    fun search(query: String): Single<SearchResult>
}
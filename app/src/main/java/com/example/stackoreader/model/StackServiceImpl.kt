package com.example.stackoreader.model

import com.example.stackoreader.data.SearchResult
import io.reactivex.Single
import javax.inject.Inject

class StackServiceImpl @Inject constructor(
    private val api: StackApi
) : StackService {

    private val ORDER = "desc"
    private val SORT = "activity"
    private val SITE = "stackoverflow"

    override fun search(query: String): Single<SearchResult> {
        return api.getSearchResults(ORDER, SORT, query, SITE)
    }

}
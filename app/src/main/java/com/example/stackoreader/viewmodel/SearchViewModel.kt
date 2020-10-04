package com.example.stackoreader.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stackoreader.data.Item
import com.example.stackoreader.data.SearchResult
import com.example.stackoreader.model.StackService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val stackService = StackService()
    private val resultList = MutableLiveData<List<Item>>()

    fun getResultsList(): LiveData<List<Item>> = resultList

    fun search(query: String) {
        disposable.add(
            stackService.search(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SearchResult>() {
                    override fun onSuccess(result: SearchResult) {
                        resultList.postValue(result.items)
//                        for (item in result.items) {
//                            Timber.d("title: ${item.title}")
//                        }

                    }

                    override fun onError(e: Throwable) {
                        Timber.e("error: %s", e.toString())
                    }

                })
        )
    }

}
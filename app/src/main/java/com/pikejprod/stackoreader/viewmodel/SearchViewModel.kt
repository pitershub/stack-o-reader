package com.pikejprod.stackoreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pikejprod.stackoreader.data.Item
import com.pikejprod.stackoreader.data.SearchResult
import com.pikejprod.stackoreader.model.StackService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor(
    private val stackService: StackService
) {
    private val disposable = CompositeDisposable()
    private val resultList = MutableLiveData<List<Item>>()
    private val loadDataError = MutableLiveData<Boolean>()
    private val loadInProgress = MutableLiveData<Boolean>()

    fun getResultsList(): LiveData<List<Item>> = resultList
    fun getLoadDataError(): LiveData<Boolean> = loadDataError
    fun getLoadInProgress(): LiveData<Boolean> = loadInProgress

    fun search(query: String) {
        disposable.add(
            stackService.search(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<SearchResult>() {
                    override fun onSuccess(result: SearchResult) {
                        Timber.d("Success")
                        loadDataError.postValue(false)
                        loadInProgress.postValue(false)
                        resultList.postValue(result.items)
                    }

                    override fun onError(e: Throwable) {
                        Timber.e("error: %s", e.toString())
                        loadDataError.postValue(true)
                        loadInProgress.postValue(false)
                    }
                })
        )
    }
}
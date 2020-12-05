package com.pikejprod.stackoreader.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pikejprod.stackoreader.data.Item
import com.pikejprod.stackoreader.data.SearchResult
import com.pikejprod.stackoreader.model.StackService
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

import com.ww.roxie.BaseViewModel
import com.ww.roxie.Reducer

@Singleton
class SearchViewModel @Inject constructor(
    initialState: StackOState?,
    private val stackService: StackService
)  : BaseViewModel<StackOAction, StackOState>() {

    override val initialState = initialState ?: StackOState(activity = false)

    private val reducer: Reducer<StackOState, StackOChange> = { state, change ->
        when (change) {
            is StackOChange.Loading -> state.copy(
                activity = true,
                displayError = false
            )
            is StackOChange.Result -> state.copy(
                activity = false,
                resultList = change.resultList,
                displayError = false
            )
            is StackOChange.Error -> state.copy(
                activity = false,
                displayError = true
            )
        }
    }

    init {
        bindActions()
    }

    private fun bindActions() {
        val getResultChange = actions
            .ofType<StackOAction.SearchButtonClicked>()
            .switchMap { action ->
                stackService.search(action.query)
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .map<StackOChange> { StackOChange.Result(it.items) }
                    .onErrorReturn { StackOChange.Error(it) }
                    .startWith(StackOChange.Loading)
            }

        disposables += getResultChange
            .scan(initialState, reducer)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(state::setValue)
    }
}
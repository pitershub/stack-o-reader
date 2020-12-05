package com.pikejprod.stackoreader.viewmodel

import com.pikejprod.stackoreader.data.Item

sealed class StackOChange {
    object Loading : StackOChange()
    data class Result(val resultList: List<Item>) : StackOChange()
    data class Error(val throwable: Throwable?) : StackOChange()
}
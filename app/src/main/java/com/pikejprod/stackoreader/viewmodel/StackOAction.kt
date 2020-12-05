package com.pikejprod.stackoreader.viewmodel

import com.ww.roxie.BaseAction

sealed class StackOAction : BaseAction {
    data class SearchButtonClicked(val query: String) : StackOAction()
}
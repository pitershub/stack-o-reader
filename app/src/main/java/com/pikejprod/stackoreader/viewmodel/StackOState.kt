package com.pikejprod.stackoreader.viewmodel

import com.pikejprod.stackoreader.data.Item
import com.ww.roxie.BaseState

data class StackOState(
    val activity: Boolean = false,
    val resultList: List<Item> = emptyList(),
    val displayError: Boolean = false
) : BaseState
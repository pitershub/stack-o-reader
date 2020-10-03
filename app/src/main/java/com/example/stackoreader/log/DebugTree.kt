package com.example.stackoreader.log

import timber.log.Timber

class DebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val tagWithPrefix = "STACKO_$tag"
        super.log(priority, tagWithPrefix, message, t)
    }
}
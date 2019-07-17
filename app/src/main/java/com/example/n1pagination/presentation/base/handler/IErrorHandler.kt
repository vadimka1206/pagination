package com.example.n1pagination.presentation.base.handler

import android.util.Log
import com.example.n1pagination.presentation.base.view.IView

interface IErrorHandler {

    fun handleError(throwable: Throwable, view: IView?) {
        Log.e(javaClass.simpleName, throwable.message, throwable)
    }
}
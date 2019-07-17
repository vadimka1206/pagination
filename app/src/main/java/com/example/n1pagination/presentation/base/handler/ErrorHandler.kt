package com.example.n1pagination.presentation.base.handler

import android.content.Context
import com.core.data.exceptions.*
import com.example.n1pagination.R
import com.example.n1pagination.presentation.base.view.IView
import retrofit2.HttpException
import java.io.IOException

class ErrorHandler(private val context: Context) : IErrorHandler {

    override fun handleError(throwable: Throwable, view: IView?) { //Logic handle error
        super.handleError(throwable, view)
        view?.showToast(throwable.userMessage(context))
    }
}

fun Throwable.userMessage(context: Context): String = when (this) {
    is HttpException -> when (this.code()) {
        304 -> context.getString(R.string.not_modified_error)
        400 -> context.getString(R.string.bad_request_error)
        401 -> context.getString(R.string.unauthorized_error)
        403 -> context.getString(R.string.forbidden_error)
        404 -> context.getString(R.string.not_found_error)
        405 -> context.getString(R.string.method_not_allowed_error)
        409 -> context.getString(R.string.conflict_error)
        422 -> context.getString(R.string.unprocessable_error)
        500 -> context.getString(R.string.server_error_error)
        else -> context.getString(R.string.unknown_error)
    }
    is NoConnectionToServerException -> this.message ?: context.getString(R.string.server_error_error)
    is SessionExpiredException -> context.getString(R.string.session_expired)
    is NoNetworkException -> context.getString(R.string.network_error)
    is TimeoutException -> context.getString(R.string.network_error)
    is TokenException -> context.getString(R.string.unauthorized_error)
    is IOException -> context.getString(R.string.network_error)
    else -> context.getString(R.string.unknown_error)
}
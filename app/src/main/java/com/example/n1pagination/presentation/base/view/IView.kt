package com.example.n1pagination.presentation.base.view

import androidx.annotation.StringRes

interface IView {

    fun hideProgress()

    fun showProgress()

    fun showToast(message: CharSequence)

    fun showToast(@StringRes stringId: Int)
}
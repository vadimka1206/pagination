package com.example.n1pagination.presentation.base.presenter

import androidx.lifecycle.Lifecycle
import com.example.n1pagination.presentation.base.view.IView

interface IPresenter<V : IView> {

    fun unBindView()

    fun bindView(view: V)

    fun onPresenterDestroy()

    fun attachLifecycle(lifecycle: Lifecycle)

    fun detachLifecycle(lifecycle: Lifecycle)
}
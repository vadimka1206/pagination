package com.example.n1pagination.presentation.features.flatoffers.presenter

import com.example.n1pagination.presentation.base.presenter.BasePresenter
import com.example.n1pagination.presentation.features.flatoffers.view.IFlatOffersView

abstract class IFlatOffersPresenter : BasePresenter<IFlatOffersView>() {

    abstract fun onCreate()

    abstract fun refresh()

    abstract fun loadMore()
}
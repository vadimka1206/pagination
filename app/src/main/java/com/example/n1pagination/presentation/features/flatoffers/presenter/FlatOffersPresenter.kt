package com.example.n1pagination.presentation.features.flatoffers.presenter

import com.core.data.exceptions.NoConnectionToServerException
import com.example.n1pagination.R
import com.example.n1pagination.data.interactors.flatoffers.IFlatOffersInteractor
import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.presentation.base.handler.IErrorHandler


class FlatOffersPresenter(
    errorHandler: IErrorHandler,
    private val interactor: IFlatOffersInteractor
) : IFlatOffersPresenter() {

    init {
        this.errorHandler = errorHandler
    }

    private var isLoading = false
    private var isConnectionError = false
    private var isRefreshing = false

    override fun onCreate() {
        isLoading = true
        runAsyncProgress(interactor.getFlatOffers(), this::handleOffers, this::handleError)
    }

    override fun refresh() {
        isRefreshing = true
        runAsync(interactor.refresh(), {
            runAsync(interactor.getFlatOffers(), this::handleNewOffers) {
                view?.stopRefresh()
                handleError(it)
            }
        }, this::handleError)
    }

    override fun loadMore() {
        if (!isLoading) {
            isLoading = true
            runAsync(interactor.loadMore(), this::handleOffers, this::handleError)
        }
    }

    private fun handleNewOffers(offers: List<FlatOffer>) {
        isConnectionError = false
        isRefreshing = false
        view?.clearList()
        view?.addItems(offers)
        view?.stopRefresh()
        isLoading = false
    }

    private fun handleOffers(offers: List<FlatOffer>) {
        isConnectionError = false
        view?.addItems(offers)
        isLoading = false
    }

    private fun handleError(throwable: Throwable) {
        isLoading = false
        if (throwable is NoConnectionToServerException) {
            if (!isConnectionError || isRefreshing) {
                view?.showToast(R.string.error_no_connection)
            }
            isConnectionError = true
            isRefreshing = false
        } else {
            view?.showToast(throwable.localizedMessage)
        }
    }
}
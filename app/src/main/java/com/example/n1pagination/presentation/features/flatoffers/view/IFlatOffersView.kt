package com.example.n1pagination.presentation.features.flatoffers.view

import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.presentation.base.view.IView

interface IFlatOffersView : IView {

    fun addItems(list: List<FlatOffer>)

    fun clearList()

    fun stopRefresh()
}
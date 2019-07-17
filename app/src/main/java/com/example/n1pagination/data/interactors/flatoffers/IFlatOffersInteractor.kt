package com.example.n1pagination.data.interactors.flatoffers

import com.example.n1pagination.data.model.FlatOffer
import io.reactivex.Completable
import io.reactivex.Single

interface IFlatOffersInteractor {

    fun getFlatOffers(): Single<List<FlatOffer>>

    fun loadMore(): Single<List<FlatOffer>>

    fun refresh(): Completable
}
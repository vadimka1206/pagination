package com.example.n1pagination.data.interactors.flatoffers

import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.data.repositories.flatsoffers.IFlatOffersRepository
import io.reactivex.Completable
import io.reactivex.Single

class FlatOffersInteractor(private val repository: IFlatOffersRepository) : IFlatOffersInteractor {

    override fun getFlatOffers(): Single<List<FlatOffer>> {
        return repository.getFlatOffers()
    }

    override fun loadMore(): Single<List<FlatOffer>> {
        return repository.loadMore()
    }

    override fun refresh(): Completable {
        return repository.refresh()
    }
}
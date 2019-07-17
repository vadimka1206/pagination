package com.example.n1pagination.data.repositories.flatsoffers

import android.util.Log
import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.data.network.service.INetworkService
import io.reactivex.Completable
import io.reactivex.Single

class FlatOffersRepository(
    private val networkService: INetworkService
) : IFlatOffersRepository {

    val offersMap: HashMap<Long, FlatOffer> = HashMap()
    var limit = 20
    var offset = 0

    override fun getFlatOffers(): Single<List<FlatOffer>> {
        return Single.fromCallable {
            Log.i("wtf", "limit " + limit + ", offset " + offset)
            val flatOffersResult = networkService.loadFlatOffers(limit, offset)
            offset = flatOffersResult.limit + flatOffersResult.offset
            if (limit + offset > flatOffersResult.count) {
                limit = flatOffersResult.count - offset
            }
            flatOffersResult.result.map { offersMap.put(it.id, it) }
            return@fromCallable flatOffersResult.result
        }
    }

    override fun loadMore(): Single<List<FlatOffer>> {
        return Single.fromCallable {
            Log.i("wtf", "limit " + limit + ", offset " + offset)
            val flatOffersResult = networkService.loadFlatOffers(limit, offset)
            offset = flatOffersResult.limit + flatOffersResult.offset
            if (limit + offset > flatOffersResult.count) {
                limit = flatOffersResult.count - offset
            }
            val newList = flatOffersResult.result.filter { !offersMap.containsKey(it.id) }
            newList.map { offersMap.put(it.id, it) }
            return@fromCallable newList
        }
    }

    override fun refresh(): Completable {
        Log.i("wtf", "refresh")
        limit = 20
        offset = 0
        offersMap.clear()
        return Completable.complete()
    }
}

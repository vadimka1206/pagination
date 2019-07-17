package com.example.n1pagination.data.network.service

import com.example.n1pagination.data.model.FlatOffersResult

interface INetworkService {

    fun loadFlatOffers(limit: Int, offset: Int): FlatOffersResult
}
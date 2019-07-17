package com.example.n1pagination.data.network.mappers

import com.example.n1pagination.data.model.FlatOffer
import com.example.n1pagination.data.model.FlatOffersResult
import com.example.n1pagination.data.network.responses.FlatOffersResponse
import com.example.n1pagination.data.network.responses.FlatServer
import java.util.*

const val DEC_IN_METERS = 100

fun FlatServer.convert(): FlatOffer {
    return FlatOffer(
        id = id,
        photos = photos?.map { it.url } ?: Collections.emptyList(),
        price = params?.price?.toInt() ?: 0,
        roomsCount = params?.roomsCount ?: 0,
        streetName = if (params != null && !params.houseAddresses.isNullOrEmpty()) {
            params.houseAddresses.first().street?.name ?: ""
        } else {
            ""
        },
        houseNumber = if (params != null && !params.houseAddresses.isNullOrEmpty()) {
            params.houseAddresses.first().houseNumber ?: ""
        } else {
            ""
        },
        totalAreaInMeters = params?.totalArea?.div(DEC_IN_METERS) ?: 0,
        livingAreaInMeters = params?.livingArea?.div(DEC_IN_METERS) ?: 0,
        kitchenAreaInMeters = params?.kitchenArea?.div(DEC_IN_METERS) ?: 0,
        floorsCount = params?.floorsCount ?: 0,
        floor = params?.floor ?: 0
    )
}

fun FlatOffersResponse.convert(): FlatOffersResult {
    return FlatOffersResult(
        limit = metadata?.resultSet?.limit ?: 0,
        offset = metadata?.resultSet?.offset ?: 0,
        count = metadata?.resultSet?.count ?: 0,
        result = if (result.isNullOrEmpty()) {
            Collections.emptyList()
        } else {
            result.map { it.convert() }
        }

    )
}
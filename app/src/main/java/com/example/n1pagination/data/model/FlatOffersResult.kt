package com.example.n1pagination.data.model

data class FlatOffersResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val result: List<FlatOffer>
)
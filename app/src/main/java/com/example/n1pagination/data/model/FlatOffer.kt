package com.example.n1pagination.data.model

import java.io.Serializable

data class FlatOffer(
    val id: Long,
    val photos: List<String>,
    val price: Int,
    val roomsCount: Int,
    val streetName: String,
    val houseNumber: String,
    val totalAreaInMeters: Int,
    val livingAreaInMeters: Int,
    val kitchenAreaInMeters: Int,
    val floorsCount: Int,
    val floor: Int
) : Serializable
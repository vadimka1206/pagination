package com.example.n1pagination.data.network.service

import com.example.n1pagination.data.network.responses.FlatOffersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {

    @GET("offers/?filter[region_id]=1054&query[0][deal_type]=sell&query[0][rubric]=flats&query[0][status]=published&sort=-creation_date")
    fun loadFlatOffers(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<FlatOffersResponse>

}
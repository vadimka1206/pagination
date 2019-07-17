package com.example.n1pagination.data.network.responses

import com.google.gson.annotations.SerializedName

open class FlatOffersResponse : BaseResponse() {

    @SerializedName("result")
    val result: List<FlatServer>? = null
    @SerializedName("metadata")
    val metadata: Metadata? = null

    open class Metadata {
        @SerializedName("resultset")
        val resultSet: ResultSet? = null
    }

    open class ResultSet {
        @SerializedName("limit")
        val limit: Int? = null
        @SerializedName("offset")
        val offset: Int? = null
        @SerializedName("count")
        val count: Int? = null
    }
}
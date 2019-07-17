package com.example.n1pagination.data.network.service

import com.core.data.exceptions.NoConnectionToServerException
import com.core.data.exceptions.NoNetworkException
import com.example.n1pagination.data.model.FlatOffersResult
import com.example.n1pagination.data.network.NetworkState
import com.example.n1pagination.data.network.ServerResponse
import com.example.n1pagination.data.network.mappers.convert
import com.example.n1pagination.data.network.responses.BaseResponse
import retrofit2.Call

class NetworkService(
    private val serverApi: ServerApi,
    private val networkState: NetworkState,
    private val serverResponse: ServerResponse
) : INetworkService {

    override fun loadFlatOffers(limit: Int, offset: Int): FlatOffersResult {
        val response = execute(serverApi.loadFlatOffers(limit, offset))
        return response.convert()
    }

    private fun <T : BaseResponse> execute(tCall: Call<T>): T {
        try {
            if (networkState.hasOnlineNetwork()) {
                return serverResponse.handleCode(tCall.execute())
            }
            throw NoNetworkException()
        } catch (e: Throwable) {
            throw NoConnectionToServerException(e.message)
        }
    }
}
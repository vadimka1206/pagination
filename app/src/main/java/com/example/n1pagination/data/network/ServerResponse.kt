package com.example.n1pagination.data.network

import com.core.data.exceptions.NoConnectionToServerException
import com.example.n1pagination.data.network.responses.BaseResponse
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ServerResponse(private val gson: Gson) {

    @Throws(IOException::class)
    fun <T : BaseResponse> handleCode(response: Response<T>): T {
        if (response.isSuccessful) {
            return response.body()?.let { it } ?: run {
                throw NoConnectionToServerException()
            }
        }
        throw HttpException(response)
    }
}
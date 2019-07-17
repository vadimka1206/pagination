package com.example.n1pagination.di.modules

import android.content.Context
import com.example.n1pagination.BuildConfig
import com.example.n1pagination.data.network.NetworkState
import com.example.n1pagination.data.network.ServerResponse
import com.example.n1pagination.data.network.service.INetworkService
import com.example.n1pagination.data.network.service.NetworkService
import com.example.n1pagination.data.network.service.ServerApi
import com.example.n1pagination.di.scopes.PerApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {
        private const val MAIN_URL = "https://api.n1.ru/api/v1/"
    }


    @Provides
    @PerApplication
    internal fun providerServerUrl(): String {
        return MAIN_URL
    }

    @Provides
    @PerApplication
    internal fun provideServerApi(retrofit: Retrofit): ServerApi {
        return retrofit.create(ServerApi::class.java)
    }

    @Provides
    @PerApplication
    internal fun provideRetrofit(builder: Retrofit.Builder, serverUrl: String): Retrofit {
        return builder.baseUrl(serverUrl).build()
    }

    @Provides
    @PerApplication
    internal fun provideRetrofitBuilder(converterFactory: Converter.Factory): Retrofit.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()


        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(converterFactory)
            .client(client)
    }

    @Provides
    @PerApplication
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .serializeNulls()
            .setLenient()
            .create()
    }

    @Provides
    @PerApplication
    internal fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @PerApplication
    internal fun providerResponse(gson: Gson): ServerResponse {
        return ServerResponse(gson)
    }

    @Provides
    @PerApplication
    internal fun provideNetworkService(
        serverApi: ServerApi,
        networkState: NetworkState,
        serverResponse: ServerResponse
    ): INetworkService {
        return NetworkService(serverApi, networkState, serverResponse)
    }

    @Provides
    @PerApplication
    internal fun provideNetworkState(context: Context): NetworkState {
        return NetworkState(context)
    }
}
package com.example.n1pagination.di

import android.content.Context
import com.example.n1pagination.data.network.service.INetworkService
import com.example.n1pagination.di.modules.AppModule
import com.example.n1pagination.di.modules.NetworkModule
import com.example.n1pagination.di.scopes.PerApplication
import com.example.n1pagination.presentation.base.handler.IErrorHandler
import dagger.Component

@PerApplication
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    val context: Context

    val errorHandler: IErrorHandler

    val networkService: INetworkService


}
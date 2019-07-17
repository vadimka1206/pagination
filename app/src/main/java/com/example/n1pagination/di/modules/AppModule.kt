package com.example.n1pagination.di.modules

import android.content.Context
import com.example.n1pagination.di.scopes.PerApplication
import com.example.n1pagination.presentation.base.handler.ErrorHandler
import com.example.n1pagination.presentation.base.handler.IErrorHandler
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    @PerApplication
    internal fun providerApplication(): Context {
        return context
    }

    @Provides
    @PerApplication
    internal fun providerHandler(context: Context): IErrorHandler {
        return ErrorHandler(context)
    }

}
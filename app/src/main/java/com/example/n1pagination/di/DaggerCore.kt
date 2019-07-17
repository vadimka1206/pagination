package com.example.n1pagination.di

import android.content.Context
import com.example.n1pagination.di.modules.AppModule

object DaggerCore {

    var appComponent: AppComponent? = null
        private set

    private fun createComponent(context: Context): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
    }

    fun init(context: Context) {
        appComponent = createComponent(context)
    }
}
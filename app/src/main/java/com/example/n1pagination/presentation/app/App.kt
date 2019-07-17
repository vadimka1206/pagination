package com.example.n1pagination.presentation.app

import android.app.Application
import com.example.n1pagination.di.DaggerCore

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerCore.init(this)
    }
}
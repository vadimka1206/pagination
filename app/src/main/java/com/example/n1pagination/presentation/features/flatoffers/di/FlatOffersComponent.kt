package com.example.n1pagination.presentation.features.flatoffers.di

import com.example.n1pagination.di.AppComponent
import com.example.n1pagination.di.scopes.PerActivity
import com.example.n1pagination.presentation.features.flatoffers.view.FlatOffersActivity
import dagger.Component

@PerActivity
@Component(modules = [FlatOffersModule::class], dependencies = [AppComponent::class])
interface FlatOffersComponent {

    fun inject(activity: FlatOffersActivity)
}
package com.example.n1pagination.presentation.features.flatoffers.di

import com.example.n1pagination.data.interactors.flatoffers.FlatOffersInteractor
import com.example.n1pagination.data.interactors.flatoffers.IFlatOffersInteractor
import com.example.n1pagination.data.network.service.INetworkService
import com.example.n1pagination.data.repositories.flatsoffers.FlatOffersRepository
import com.example.n1pagination.data.repositories.flatsoffers.IFlatOffersRepository
import com.example.n1pagination.di.scopes.PerActivity
import com.example.n1pagination.presentation.base.handler.IErrorHandler
import com.example.n1pagination.presentation.features.flatoffers.presenter.FlatOffersPresenter
import com.example.n1pagination.presentation.features.flatoffers.presenter.IFlatOffersPresenter
import dagger.Module
import dagger.Provides

@Module
class FlatOffersModule {

    @PerActivity
    @Provides
    fun providePresenter(errorMessage: IErrorHandler, interactor: IFlatOffersInteractor): IFlatOffersPresenter {
        return FlatOffersPresenter(errorMessage, interactor)
    }

    @PerActivity
    @Provides
    fun provideInteractor(repository: IFlatOffersRepository): IFlatOffersInteractor {
        return FlatOffersInteractor(repository)
    }

    @PerActivity
    @Provides
    fun provideRepository(
        networkService: INetworkService
    ): IFlatOffersRepository {
        return FlatOffersRepository(networkService)
    }
}
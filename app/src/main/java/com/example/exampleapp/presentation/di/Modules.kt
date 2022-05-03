package com.example.exampleapp.presentation.di

import com.example.exampleapp.data.MagicDataRepository
import com.example.exampleapp.data.MagicDataStore
import com.example.exampleapp.datasources.cache.MagicCacheImpl
import com.example.exampleapp.datasources.cache.MemoryCache
import com.example.exampleapp.datasources.remote.MagicRemoteImpl
import com.example.exampleapp.datasources.remote.MagicServiceFactory
import com.example.exampleapp.domain.interactor.GetCards
import com.example.exampleapp.domain.repository.MagicRepository
import com.example.exampleapp.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { MemoryCache() }
    single { MagicServiceFactory.magicService() }
}

val homeModule = module {
    // We have two classes of the same type, we need to add a name for each one
    factory<MagicDataStore>(named("magicCacheDataStore")) { MagicCacheImpl(get()) }
    factory<MagicDataStore>(named("magicRemoteDataStore")) { MagicRemoteImpl(get()) }

    factory<MagicRepository> {
        MagicDataRepository(
            get(named("magicCacheDataStore")),
            get(named("magicRemoteDataStore"))
        )
    }

    factory { GetCards(get()) }

    viewModel { HomeViewModel(get()) }
}
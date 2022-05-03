package com.example.exampleapp.data

import com.example.exampleapp.domain.repository.MagicRepository
import com.example.exampleapp.model.Cards
import io.reactivex.Single

class MagicDataRepository(
    private val cacheDataStore: MagicDataStore,
    private val remoteDataStore: MagicDataStore
) : MagicRepository {

    override fun getCards(): Single<List<Cards>> {
        // Here we can switch local / remote repositories
        return cacheDataStore.getCards().flatMap { cacheCards ->
            if (cacheCards.isEmpty()) {
                // If we do not have cached cards, download it from remote and save in cache
                remoteDataStore.getCards().flatMap { remoteCards ->
                    cacheDataStore.saveCards(remoteCards).toSingle { remoteCards }
                }
            } else {
                // We have cached cards, just return it as a Single
                Single.just(cacheCards)
            }
        }
    }

}
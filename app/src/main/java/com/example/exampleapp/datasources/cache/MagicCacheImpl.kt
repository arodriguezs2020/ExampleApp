package com.example.exampleapp.datasources.cache

import com.example.exampleapp.data.MagicDataStore
import com.example.exampleapp.model.Cards
import io.reactivex.Completable
import io.reactivex.Single

class MagicCacheImpl(private val memoryCache: MemoryCache) : MagicDataStore {

    override fun getCards(): Single<List<Cards>> {
        return memoryCache.getCards()
    }

    override fun saveCards(cards: List<Cards>): Completable {
        memoryCache.cards = cards
        return Completable.complete() // We need to return a completable
    }

}
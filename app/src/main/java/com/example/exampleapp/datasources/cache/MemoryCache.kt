package com.example.exampleapp.datasources.cache

import com.example.exampleapp.model.Cards
import io.reactivex.Single

/**
 * This class is used to implement an in-memory cache,
 * which does not survive between app executions.
 *
 * Normally a database is used instead.
 * https://developer.android.com/training/data-storage/room
 *
 * @property cards List<Cards>?
 */
class MemoryCache {

    var cards: List<Cards>? = null

    fun getCards(): Single<List<Cards>> = cards.orEmpty().let { Single.just(it) }

    fun clearAll() {
        cards = null
    }

}
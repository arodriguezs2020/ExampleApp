package com.example.exampleapp.datasources.remote

import com.example.exampleapp.data.MagicDataStore
import com.example.exampleapp.model.Cards
import io.reactivex.Completable
import io.reactivex.Single

class MagicRemoteImpl(private val magicService: MagicService): MagicDataStore {

    override fun getCards(): Single<List<Cards>> {
        return magicService.getCards().map {
            it.cards
        }
    }

    override fun saveCards(cards: List<Cards>): Completable {
        // Only used from cache
        TODO("Not yet implemented")
    }

}
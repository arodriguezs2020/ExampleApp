package com.example.exampleapp.data

import com.example.exampleapp.model.Cards
import io.reactivex.Completable
import io.reactivex.Single

interface MagicDataStore {

    fun getCards(): Single<List<Cards>>

    fun saveCards(cards: List<Cards>): Completable

}
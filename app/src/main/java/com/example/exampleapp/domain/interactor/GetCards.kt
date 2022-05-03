package com.example.exampleapp.domain.interactor

import com.example.exampleapp.domain.repository.MagicRepository
import com.example.exampleapp.model.Cards
import io.reactivex.Single

class GetCards(private val magicRepository: MagicRepository) {

    fun execute(): Single<List<Cards>> {
        return magicRepository.getCards()
    }

}
package com.example.exampleapp.domain.repository

import com.example.exampleapp.model.Cards
import io.reactivex.Single

interface MagicRepository {

    fun getCards(): Single<List<Cards>>

}
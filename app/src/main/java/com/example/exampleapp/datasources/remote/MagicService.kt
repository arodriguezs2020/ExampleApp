package com.example.exampleapp.datasources.remote

import com.example.exampleapp.datasources.remote.model.CardListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MagicService {

    companion object {
        const val BASE_URL = "https://api.magicthegathering.io/v1/"
        private const val CARDS_PATH = "cards"
    }

    @GET(CARDS_PATH)
    fun getCards(): Single<CardListResponse>

}
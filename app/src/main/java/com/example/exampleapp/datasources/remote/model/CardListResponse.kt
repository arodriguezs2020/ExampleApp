package com.example.exampleapp.datasources.remote.model

import com.example.exampleapp.model.Cards
import com.google.gson.annotations.SerializedName

data class CardListResponse(
    @SerializedName("cards") val cards : List<Cards>
)
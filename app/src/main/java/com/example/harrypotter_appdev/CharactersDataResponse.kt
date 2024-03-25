package com.example.harrypotter_appdev

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class CharactersDataResponse(
    val characters: List<CharacterInformationResponse>
)

data class CharacterInformationResponse(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("species") val species: String
)
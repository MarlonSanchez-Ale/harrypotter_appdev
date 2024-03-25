package com.example.harrypotter_appdev

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("species") val species: String
)
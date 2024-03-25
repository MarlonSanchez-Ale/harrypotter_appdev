package com.example.harrypotter_appdev

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/characters")
    fun getAllCharacters(): Call<List<Character>>

}
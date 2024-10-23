package com.yorth21.rickandmorty.data.interfaces

import com.yorth21.rickandmorty.data.models.characters.ResponseCharacters
import retrofit2.http.GET
import retrofit2.http.Query

interface ICharacterRepository {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): ResponseCharacters
}
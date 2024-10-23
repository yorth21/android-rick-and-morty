package com.yorth21.rickandmorty.data.interfaces

import com.yorth21.rickandmorty.data.models.characters.ResponseCharacters
import retrofit2.http.GET

interface ICharacterRepository {
    @GET("character")
    suspend fun getCharacters(): ResponseCharacters
}
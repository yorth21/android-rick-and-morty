package com.yorth21.rickandmorty.data.repositories

import com.yorth21.rickandmorty.data.interfaces.ICharacterRepository
import com.yorth21.rickandmorty.data.models.characters.ResponseCharacters
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterRepository: ICharacterRepository
) {
    suspend fun getCharacters(page: Int): ResponseCharacters {
        return characterRepository.getCharacters(page)
    }
}
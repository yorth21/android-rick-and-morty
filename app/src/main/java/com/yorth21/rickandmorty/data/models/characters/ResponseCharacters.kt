package com.yorth21.rickandmorty.data.models.characters

data class ResponseCharacters(
    val info: Info,
    val results: List<Result>
)
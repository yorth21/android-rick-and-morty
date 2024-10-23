package com.yorth21.rickandmorty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yorth21.rickandmorty.data.models.characters.ResponseCharacters
import com.yorth21.rickandmorty.data.models.characters.Result
import com.yorth21.rickandmorty.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
): ViewModel() {
    private val _state = MutableStateFlow(emptyList<Result>())
    val state: StateFlow<List<Result>>
        get() = _state

    init {
        viewModelScope.launch {
            _state.value = characterRepository.getCharacters().results
        }
    }
}
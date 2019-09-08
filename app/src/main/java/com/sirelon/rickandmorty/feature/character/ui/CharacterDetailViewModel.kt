package com.sirelon.rickandmorty.feature.character.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.sirelon.rickandmorty.feature.base.BaseViewModel
import com.sirelon.rickandmorty.feature.character.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val charactersRepository: CharactersRepository) :
    BaseViewModel() {

    private val idTrigger = MutableLiveData<Long>()

    val characterDetailsLiveData = idTrigger.switchMap { id ->
        charactersRepository.loadItemById(id).map { result ->
            result.exceptionOrNull()?.let(this::onError)
            result.getOrNull()
        }
    }

    fun setId(id: Long) {
        idTrigger.postValue(id)
    }

    fun changeFavoriteState() {
        val item = characterDetailsLiveData.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepository.changeFavoriteState(item)
        }
    }
}

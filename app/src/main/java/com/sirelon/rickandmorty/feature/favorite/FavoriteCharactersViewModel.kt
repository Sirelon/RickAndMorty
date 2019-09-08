package com.sirelon.rickandmorty.feature.favorite

import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import com.sirelon.rickandmorty.feature.base.BaseViewModel
import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.feature.character.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteCharactersViewModel(private val repository: CharactersRepository) : BaseViewModel() {
    val allRepositories = repository.loadAllFavorites().toLiveData(10)

    fun removeItem(item: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromFavorite(item)
        }
    }
}
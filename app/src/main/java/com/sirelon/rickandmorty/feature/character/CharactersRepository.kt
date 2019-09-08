package com.sirelon.rickandmorty.feature.character

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.sirelon.rickandmorty.feature.search.network.CharactersApi

/**
 * Created on 2019-09-07 13:18 for RickAndMorty.
 */
class CharactersRepository(
    private val charactersDao: CharactersDao,
    private val api: CharactersApi
) {

    fun loadAllFavorites() = charactersDao.loadAllFavorites()

    @WorkerThread
    fun loadItemById(id: Long): LiveData<Result<Character>> {
        // First fetch from DataBase, than from remote
        return charactersDao.loadItem(id).switchMap {
            liveData {
                if (it == null) {
                    val remoteResult = runCatching { api.loadDetails(id)!!.map() }
                    emit(remoteResult)
                } else {
                    // Emit item from database
                    emit(Result.success(it))
                }
            }
        }
    }

    @WorkerThread
    suspend fun addToFavorite(item: Character) {
        item.isFavorite = true
        charactersDao.insert(item)
    }

    @WorkerThread
    suspend fun removeFromFavorite(character: Character) {
        charactersDao.delete(character)
    }

    @WorkerThread
    suspend fun isCharacterFavorite(character: Character): Boolean {
        return charactersDao.isItemFavorite(character.id)
    }

    suspend fun changeFavoriteState(item: Character) {
        if (item.isFavorite) {
            removeFromFavorite(item)
        } else {
            addToFavorite(item)
        }
    }
}
package com.sirelon.rickandmorty.feature.character

import android.util.Log
import androidx.annotation.WorkerThread

/**
 * Created on 2019-09-07 13:18 for RickAndMorty.
 */
class CharactersRepository(private val charactersDao: CharactersDao) {

    fun loadAll() = charactersDao.loadAllFavorites()

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
    suspend fun updateCharactersList(list: List<Character>) {
        val updated = charactersDao.updateAll(list)
        Log.d("Sirelon", "updateCharactersList: updated $updated")
    }

    @WorkerThread
    suspend fun isCharacterFavorite(character: Character): Boolean {
        return charactersDao.isItemFavorite(character.id)
    }

}
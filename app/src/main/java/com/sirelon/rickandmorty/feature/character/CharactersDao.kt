package com.sirelon.rickandmorty.feature.character

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.sirelon.rickandmorty.database.CommonDao

/**
 * Created on 2019-09-05 20:52 for RickAndMorty.
 */
@Dao
interface CharactersDao : CommonDao<Character> {
    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun loadAllFavorites(): DataSource.Factory<Int, Character>

    @Query("SELECT isFavorite FROM characters WHERE id = :id")
    fun isItemFavorite(id: Long): Boolean

    @Query("SELECT * FROM characters WHERE id = :id")
    fun loadItem(id: Long): LiveData<Character?>
}
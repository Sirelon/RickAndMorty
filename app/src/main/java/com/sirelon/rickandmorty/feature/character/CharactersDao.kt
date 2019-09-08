package com.sirelon.rickandmorty.feature.character

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sirelon.rickandmorty.database.CommonDao

/**
 * Created on 2019-09-05 20:52 for RickAndMorty.
 */
@Dao
interface CharactersDao : CommonDao<Character> {
    @Query("SELECT * FROM characters")
    fun loadAll(): LiveData<List<Character>>
}
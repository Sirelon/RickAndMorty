package com.sirelon.rickandmorty.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.feature.character.CharactersDao

/**
 * Created on 2019-09-05 20:49 for RickAndMorty.
 */
@Database(entities = [Character::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
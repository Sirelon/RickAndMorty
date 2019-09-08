package com.sirelon.rickandmorty.feature.character

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on 2019-09-05 20:51 for RickAndMorty.
 */
@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    val id: Long,
    val name: String,
    val imageUrl: String,

    val isAlive: Boolean,
    val race: String
)

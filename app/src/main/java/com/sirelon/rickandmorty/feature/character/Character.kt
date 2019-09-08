package com.sirelon.rickandmorty.feature.character

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created on 2019-09-05 20:51 for RickAndMorty.
 */
@Entity(tableName = "characters")
@Parcelize
data class Character(
    @PrimaryKey
    val id: Long,
    val name: String,
    val imageUrl: String,

    val isAlive: Boolean,
    val race: String,

    var isFavorite: Boolean = false

) : Parcelable

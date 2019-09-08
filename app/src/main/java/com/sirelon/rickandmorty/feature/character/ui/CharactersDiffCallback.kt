package com.sirelon.rickandmorty.feature.character.ui

import androidx.recyclerview.widget.DiffUtil
import com.sirelon.rickandmorty.feature.character.Character

/**
 * Created on 2019-09-08 14:02 for RickAndMorty.
 */
object CharactersDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}
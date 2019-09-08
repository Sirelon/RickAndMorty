package com.sirelon.rickandmorty.feature.character.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirelon.rickandmorty.R
import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.utils.inflate
import kotlinx.android.extensions.LayoutContainer

/**
 * Created on 2019-09-05 22:09 for RickAndMorty.
 */
class CharactersPagedListAdapter(private val onItemClick: (character: Character) -> Unit) :
    PagedListAdapter<Character, CharactersPagedListAdapter.FavoriteCharacterViewHolder>(
        CharactersDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteCharacterViewHolder(parent.inflate(R.layout.item_fav_character))

    override fun onBindViewHolder(holder: FavoriteCharacterViewHolder, position: Int) {
        val character = getItem(position) ?: return
        holder.itemView.setOnClickListener { onItemClick(character) }
    }

    class FavoriteCharacterViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView)
}
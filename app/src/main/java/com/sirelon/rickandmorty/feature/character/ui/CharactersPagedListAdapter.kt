package com.sirelon.rickandmorty.feature.character.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sirelon.rickandmorty.R
import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.utils.inflate
import com.sirelon.rickandmorty.utils.loadImageUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_fav_character.*

/**
 * Created on 2019-09-05 22:09 for RickAndMorty.
 */
class CharactersPagedListAdapter(
    private val onItemClick: (character: Character) -> Unit,
    private val onFavoriteClick: ((character: Character) -> Unit)? = null
) :
    PagedListAdapter<Character, CharactersPagedListAdapter.FavoriteCharacterViewHolder>(
        CharactersDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteCharacterViewHolder(parent.inflate(R.layout.item_fav_character))

    override fun onBindViewHolder(holder: FavoriteCharacterViewHolder, position: Int) {
        val character = getItem(position) ?: return
        with(holder) {
            itemView.setOnClickListener { onItemClick(character) }
            characterName.text = character.name
            characterImage.loadImageUrl(character.imageUrl)
            if (onFavoriteClick == null) {
                // we don't have any handlers for favorite icon. So, we can hide it all
                actionFavorite.visibility = View.GONE
            } else {
                actionFavorite.isSelected = character.isFavorite
                actionFavorite.setOnClickListener {
                    onFavoriteClick.invoke(character)
                    // Update visual state for item
                    actionFavorite.isSelected = !character.isFavorite
                }
            }
        }
    }

    class FavoriteCharacterViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView)
}
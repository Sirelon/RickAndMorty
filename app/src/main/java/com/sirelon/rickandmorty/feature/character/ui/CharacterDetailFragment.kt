package com.sirelon.rickandmorty.feature.character.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.sirelon.rickandmorty.R
import com.sirelon.rickandmorty.feature.base.BaseFragment
import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.utils.loadImageUrl
import kotlinx.android.synthetic.main.empty_layout.*
import kotlinx.android.synthetic.main.fragment_character_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : BaseFragment(R.layout.fragment_character_detail) {

    companion object {
        private const val CHARACTER_ID_ARG = ".characterId"

        fun createArgs(character: Character) = bundleOf(CHARACTER_ID_ARG to character.id)
    }

    private val viewModel by viewModel<CharacterDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // I wanna crash, if someone tryies to open this fragment without arguments!
        val id = arguments!!.getLong(CHARACTER_ID_ARG)
        viewModel.setId(id)

        emptyView.setOnClickListener {
            findNavController().popBackStack()
        }

        detailsGroup.visibility = View.GONE
        viewModel.characterDetailsLiveData.observe(this) {
            Log.i("Sirelon", "Character $it")

            TransitionManager.beginDelayedTransition(detailsRoot)
            if (it == null) {
                emptyView.visibility = View.VISIBLE
                detailsGroup.visibility = View.GONE
            } else {
                emptyView.visibility = View.GONE
                detailsGroup.visibility = View.VISIBLE
                showInformation(it)
            }

            actionFavorite.setOnClickListener {
                viewModel.changeFavoriteState()
            }
        }
    }

    private fun showInformation(character: Character) {
        characterImage.loadImageUrl(character.imageUrl)
        characterName.text = character.name
        actionFavorite.isSelected = character.isFavorite
    }
}

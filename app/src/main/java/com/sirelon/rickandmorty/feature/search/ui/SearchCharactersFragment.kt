package com.sirelon.rickandmorty.feature.search.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sirelon.rickandmorty.R
import com.sirelon.rickandmorty.feature.base.BaseFragment
import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.feature.character.ui.CharacterDetailFragment
import com.sirelon.rickandmorty.feature.character.ui.CharactersPagedListAdapter
import com.sirelon.rickandmorty.utils.hideKeyboard
import com.sirelon.rickandmorty.utils.onTextChange
import kotlinx.android.synthetic.main.fragment_search_characters.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCharactersFragment : BaseFragment(R.layout.fragment_search_characters) {

    private val viewModel by viewModel<SearchCharactersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.hideKeyboard()

        subsribeForErrors(viewModel)

        val searchAdapter =
            CharactersPagedListAdapter(this::openDetails, viewModel::changeFavoriteState)
        with(repositoriesList) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = searchAdapter
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
        }

        viewModel.characterListLiveData.observe(this, searchAdapter::submitList)
        searchInput.editText?.onTextChange {
            viewModel.onSearchTyped(it?.toString())
        }
    }

    private fun openDetails(character: Character) {
        val args = CharacterDetailFragment.createArgs(character)
        findNavController().navigate(R.id.action_navigation_home_to_characterDetailFragment, args)
    }
}
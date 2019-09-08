package com.sirelon.rickandmorty.feature.search.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.sirelon.rickandmorty.R
import com.sirelon.rickandmorty.feature.base.BaseFragment
import com.sirelon.rickandmorty.feature.character.ui.CharactersPagedListAdapter
import com.sirelon.rickandmorty.utils.onTextChange
import kotlinx.android.synthetic.main.fragment_search_characters.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCharactersFragment : BaseFragment(R.layout.fragment_search_characters) {

    private val viewModel by viewModel<SearchCharactersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subsribeForErrors(viewModel)

        val searchAdapter = CharactersPagedListAdapter(viewModel::markAsViewed)
        with(repositoriesList) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = searchAdapter
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
        }

        viewModel.characterListLiveData.observe(this, searchAdapter::submitList)
        searchInput.editText?.onTextChange {
            viewModel.onSearchTyped(it?.toString())
        }
    }
}
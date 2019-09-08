package com.sirelon.rickandmorty.feature.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.sirelon.rickandmorty.R
import com.sirelon.rickandmorty.feature.base.BaseFragment
import com.sirelon.rickandmorty.feature.character.ui.CharacterDetailFragment
import com.sirelon.rickandmorty.feature.character.ui.CharactersPagedListAdapter
import com.sirelon.rickandmorty.utils.hideKeyboard
import kotlinx.android.synthetic.main.empty_layout.*
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_search_characters.repositoriesList
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteCharactersFragment : BaseFragment(R.layout.fragment_favorite_list) {

    private val viewModel by viewModel<FavoriteCharactersViewModel>()
    private val favCharactersAdapter = CharactersPagedListAdapter(
        onItemClick = {
            val args = CharacterDetailFragment.createArgs(it)
            findNavController().navigate(
                R.id.action_navigation_favorite_to_characterDetailFragment,
                args
            )
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.hideKeyboard()

        subsribeForErrors(viewModel)

        with(repositoriesList) {
            layoutManager = LinearLayoutManager(context)
            adapter = favCharactersAdapter
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
        }

        emptyView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.navigation_home))

        viewModel.allRepositories.observe(this) {
            TransitionManager.beginDelayedTransition(savedRoot)
            if (it.isEmpty()) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.GONE
            }
            favCharactersAdapter.submitList(it)
        }
    }
}
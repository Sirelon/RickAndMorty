package com.sirelon.rickandmorty.feature.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.sirelon.rickandmorty.R
import com.sirelon.rickandmorty.feature.base.BaseFragment
import com.sirelon.rickandmorty.feature.character.ui.CharactersPagedListAdapter
import com.sirelon.rickandmorty.utils.hideKeyboard
import com.sirelon.rickandmorty.utils.openBrowser
import kotlinx.android.synthetic.main.fragment_favorite_list.*
import kotlinx.android.synthetic.main.fragment_search_characters.repositoriesList
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteCharactersFragment : BaseFragment(R.layout.fragment_favorite_list) {

    private val viewModel by viewModel<FavoriteCharactersViewModel>()
    private val favCharactersAdapter = CharactersPagedListAdapter(
        onItemClick = {
            activity?.openBrowser(it.imageUrl)
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

            // Drag&drop, swipeToDelete interactions.
//            val itemTouchCallback = createItemTouchCallback()
//            val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
//            itemTouchHelper.attachToRecyclerView(this)
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

//    private fun createItemTouchCallback(): ItemTouchHelper.SimpleCallback {
//        return object : ItemTouchHelper.SimpleCallback(
//            ItemTouchHelper.UP or ItemTouchHelper.DOWN or
//                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
//            ItemTouchHelper.END
//        ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//
//                val position = viewHolder.adapterPosition
//                val currentList = favCharactersAdapter.currentList.toMutableList()
//                val item = currentList.getOrNull(position) ?: return false
//
//                val positionTarget = target.adapterPosition
//                val itemTarget = currentList.getOrNull(positionTarget) ?: return false
//
//                //FIXME: Incorect reording when drag fast
//                currentList[position] = itemTarget
//                currentList[positionTarget] = item
//                favCharactersAdapter.submitList(currentList)
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position = viewHolder.adapterPosition
//                val item = favCharactersAdapter.currentList.getOrNull(position) ?: return
//                viewModel.removeItem(item)
//                // TODO: Show some snackbar with "undo"
//            }
//
//            override fun clearView(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder
//            ) {
//                super.clearView(recyclerView, viewHolder)
//                // Here we will update ordering on database
//                viewModel.updatePriorityForList(favCharactersAdapter.currentList)
//            }
//
//            override fun onChildDraw(
//                c: Canvas, recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
//                actionState: Int, isCurrentlyActive: Boolean
//            ) {
//
//                val itemView = viewHolder.itemView
//
//                // TODO: highlight somehow item, which is dragging
//
//                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                    val width = itemView.width.toFloat()
//                    val alpha = 1.0f - abs(dX) / width
//                    itemView.alpha = alpha
//                    itemView.translationX = dX
//                } else {
//                    super.onChildDraw(
//                        c, recyclerView, viewHolder, dX, dY,
//                        actionState, isCurrentlyActive
//                    )
//                }
//            }
//        }
//    }
}
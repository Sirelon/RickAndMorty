package com.sirelon.rickandmorty.feature.search

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.sirelon.rickandmorty.feature.character.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created on 2019-09-07 23:40 for RickAndMorty.
 */
class SearchDataSourceFactory(
    private val searchRepository: SearchRepository,
    private var coroutineScope: CoroutineScope
) : DataSource.Factory<Int, Character>() {

    private var dataSource = dataSource(searchRepository, "")

    override fun create(): DataSource<Int, Character> = dataSource

    fun update(keyword: String) {
        dataSource.invalidate()
        dataSource =
            dataSource(searchRepository, keyword)
    }

    private fun dataSource(searchRepository: SearchRepository, keyword: String) =
        SearchGroupDataSource(searchRepository, keyword, coroutineScope)
}

private class SearchGroupDataSource(
    private val searchRepository: SearchRepository,
    private val keyword: String,
    private val coroutineScope: CoroutineScope
) : PositionalDataSource<Character>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        coroutineScope.launch {
            val list =
                searchRepository.searchByName(keyword, params.startPosition / params.loadSize)
            callback.onResult(list)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        coroutineScope.launch {
            val list = searchRepository.searchByName(keyword, 1)
            callback.onResult(list, 0)
        }
    }
}

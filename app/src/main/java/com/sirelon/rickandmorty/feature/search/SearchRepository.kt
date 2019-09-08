package com.sirelon.rickandmorty.feature.search

import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.feature.character.map
import com.sirelon.rickandmorty.feature.search.network.CharactersApi
import com.sirelon.rickandmorty.feature.search.network.ServerCharacter

/**
 * Created on 2019-09-05 21:27 for RickAndMorty.
 */
class SearchRepository(private val charactersApi: CharactersApi) {

    suspend fun searchByName(query: String, page: Int): List<Character> {
        val response = charactersApi.searchRepositories(query, page)
        return response.result
            .asSequence()
            .map(ServerCharacter::map)
            .toList()
    }
}
package com.sirelon.rickandmorty.feature.search

import com.sirelon.rickandmorty.feature.character.Character
import com.sirelon.rickandmorty.feature.search.network.SearchApi
import com.sirelon.rickandmorty.feature.search.network.ServerCharacter

/**
 * Created on 2019-09-05 21:27 for RickAndMorty.
 */
class SearchRepository(private val searchApi: SearchApi) {

    suspend fun searchByName(query: String, page: Int): List<Character> {
        val response = searchApi.searchRepositories(query, page)
        return response.result.map(ServerCharacter::map)
    }
}

fun ServerCharacter.map() =
    Character(
        id = id,
        name = name,
        isAlive = status.equals("Alive", ignoreCase = true),
        imageUrl = image,
        race = species
    )
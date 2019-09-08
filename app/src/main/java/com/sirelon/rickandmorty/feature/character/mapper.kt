package com.sirelon.rickandmorty.feature.character

import com.sirelon.rickandmorty.feature.search.network.ServerCharacter

/**
 * Created on 2019-09-08 20:48 for RickAndMorty.
 */
fun ServerCharacter.map() =
    Character(
        id = id,
        name = name,
        isAlive = status.equals("Alive", ignoreCase = true),
        imageUrl = image,
        race = species
    )
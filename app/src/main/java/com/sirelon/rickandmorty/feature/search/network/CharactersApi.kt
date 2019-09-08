package com.sirelon.rickandmorty.feature.search.network

import androidx.annotation.Keep
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created on 2019-09-05 21:03 for RickAndMorty.
 */
private const val ENDPOINT_PATH = "character"

@Keep
interface CharactersApi {

    @GET(ENDPOINT_PATH)
    suspend fun searchRepositories(
        @Query("name")
        query: String,
        @Query("page")
        page: Int
    ): ServerSearchResults

    @GET("$ENDPOINT_PATH/{id}")
    suspend fun loadDetails(
        @Path("id")
        id: Long
    ): ServerCharacter?

}
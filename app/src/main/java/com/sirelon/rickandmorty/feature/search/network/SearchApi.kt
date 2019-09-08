package com.sirelon.rickandmorty.feature.search.network

import androidx.annotation.Keep
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on 2019-09-05 21:03 for RickAndMorty.
 */
@Keep
interface SearchApi {

    @GET("character")
    suspend fun searchRepositories(
        @Query("name")
        query: String,
        @Query("page")
        page: Int
    ): ServerSearchResults

}
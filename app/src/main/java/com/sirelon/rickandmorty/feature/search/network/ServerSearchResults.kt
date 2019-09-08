package com.sirelon.rickandmorty.feature.search.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created on 2019-09-05 21:09 for RickAndMorty.
 */
@Keep
class ServerSearchResults(
    @SerializedName("info")
    val searchInfo: SearchInfo,
    @SerializedName("results")
    val result: List<ServerCharacter>
)

@Keep
class ServerCharacter(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("image")
    val image: String
)

@Keep
class SearchInfo(
    @SerializedName("count")
    val status: Int,
    @SerializedName("pages")
    val pages: String
)
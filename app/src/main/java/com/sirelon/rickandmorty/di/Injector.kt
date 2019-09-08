package com.sirelon.rickandmorty.di

import android.app.Application
import androidx.room.Room
import com.sirelon.rickandmorty.database.AppDataBase
import com.sirelon.rickandmorty.feature.character.CharactersRepository
import com.sirelon.rickandmorty.feature.favorite.FavoriteCharactersViewModel
import com.sirelon.rickandmorty.feature.search.SearchRepository
import com.sirelon.rickandmorty.feature.search.network.SearchApi
import com.sirelon.rickandmorty.feature.search.ui.SearchCharactersViewModel
import com.sirelon.rickandmorty.network.createSimpleRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created on 2019-09-05 20:27 for RickAndMorty.
 */
object Injector {

    private const val BASE_URL: String = "baseUrl"

    /**
     * Should be called only once per application start
     */
    fun init(application: Application) {
        startKoin {
            properties(mapOf(BASE_URL to "https://rickandmortyapi.com/api/"))
            androidLogger()
            androidContext(application)
            modules(
                listOf(
                    commonModule(),
                    repositoryModule(),
                    searchModule(),
                    viewedRepositoriesModule()
                )
            )
        }
    }

    /**
     * Create module for common (shared) definitions, which can be used in any features
     */
    private fun commonModule() = module {
        single { createSimpleRetrofit(androidContext(), getProperty(BASE_URL)) }
        single {
            Room.databaseBuilder(
                androidContext(),
                AppDataBase::class.java,
                ".rickAndMortyDatabase"
            ).build()
        }
    }

    /**
     * Module for repository feature
     */
    private fun repositoryModule() = module {
        single { get<AppDataBase>().charactersDao() }
        factory { CharactersRepository(get()) }
    }

    /**
     * Module for search repositories feature
     */
    private fun searchModule() = module {
        single { get<Retrofit>().create(SearchApi::class.java) }
        factory { SearchRepository(get()) }
        viewModel { SearchCharactersViewModel(get(), get()) }
    }

    private fun viewedRepositoriesModule() = module {
        viewModel { FavoriteCharactersViewModel(get()) }
    }
}
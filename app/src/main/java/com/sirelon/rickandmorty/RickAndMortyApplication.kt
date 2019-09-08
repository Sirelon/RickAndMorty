package com.sirelon.rickandmorty

import android.app.Application
import com.sirelon.rickandmorty.di.Injector

/**
 * Created on 2019-09-05 20:27 for RickAndMorty.
 */
class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }

}
package com.github.jaydeepw.pokemondirectory.di

import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.db.PokemonRepository
import com.github.jaydeepw.pokemondirectory.presenters.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PresenterModule(
    var view: MainContractInterface.View,
    var pokemonRepository: PokemonRepository
) {
    @Provides
    @Singleton
    internal fun providesMainPresenter(): MainPresenter {
        return MainPresenter(view, pokemonRepository)
    }

/*
    @Provides
    @Singleton
    internal fun providesMainPresenter(): MainPresenter {
        return DetailPresenter(view, pokemonRepository)
    }
*/
}
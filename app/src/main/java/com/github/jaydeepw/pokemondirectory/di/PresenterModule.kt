package com.github.jaydeepw.pokemondirectory.di

import com.github.jaydeepw.pokemondirectory.contracts.DetailsContractInterface
import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.db.PokemonRepository
import com.github.jaydeepw.pokemondirectory.presenters.DetailsPresenter
import com.github.jaydeepw.pokemondirectory.presenters.MainPresenter
import com.github.jaydeepw.pokemondirectory.views.BaseView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PresenterModule(
    var view: BaseView,
    var pokemonRepository: PokemonRepository
) {
    @Provides
    @Singleton
    internal fun providesMainPresenter(): MainPresenter {
        return MainPresenter(view as MainContractInterface.View, pokemonRepository)
    }

    @Provides
    @Singleton
    internal fun providesDetailsPresenter(): DetailsPresenter {
        return DetailsPresenter(view as DetailsContractInterface.View, pokemonRepository)
    }
}
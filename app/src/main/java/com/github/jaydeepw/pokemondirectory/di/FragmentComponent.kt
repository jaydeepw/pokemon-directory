package com.github.jaydeepw.pokemondirectory.di

import com.github.jaydeepw.pokemondirectory.db.PokemonRepository
import com.github.jaydeepw.pokemondirectory.models.datasource.mock.MainMockModel
import com.github.jaydeepw.pokemondirectory.models.datasource.network.MainNetworkModel
import com.github.jaydeepw.pokemondirectory.presenters.MainPresenter
import com.github.jaydeepw.pokemondirectory.views.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    PresenterModule::class,
    ModelsModule::class,
    NetworkModule::class
])
interface FragmentComponent {
    fun inject(fragment: MainFragment)
    fun inject(presenter: MainPresenter)
    fun inject(repository: PokemonRepository)
    fun inject(mainModel: MainNetworkModel)
    fun inject(mainModel: MainMockModel)
    // fun inject(fragment: DetailsFragment)
}

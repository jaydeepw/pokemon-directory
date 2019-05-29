package com.github.jaydeepw.pokemondirectory.di

import com.github.jaydeepw.pokemondirectory.models.datasource.mock.MainMockModel
import com.github.jaydeepw.pokemondirectory.models.datasource.network.DetailsNetworkModel
import com.github.jaydeepw.pokemondirectory.models.datasource.network.MainNetworkModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ModelsModule {
    @Provides
    @Singleton
    internal fun providesMainNetworkModel(): MainNetworkModel {
        return MainNetworkModel()
    }

    @Provides
    @Singleton
    internal fun providesMainMockModel(): MainMockModel {
        return MainMockModel()
    }

    @Provides
    @Singleton
    internal fun providesDetailsNetworkModel(): DetailsNetworkModel {
        return DetailsNetworkModel()
    }
}
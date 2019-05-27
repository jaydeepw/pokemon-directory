package com.github.jaydeepw.pokemondirectory.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(internal var application: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return application
    }
}
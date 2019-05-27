package com.github.jaydeepw.pokemondirectory.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class])
interface AppComponent

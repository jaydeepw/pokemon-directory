package com.github.jaydeepw.pokemondirectory

import android.app.Application
import com.github.jaydeepw.pokemondirectory.di.AppComponent
import com.github.jaydeepw.pokemondirectory.di.AppModule
import com.github.jaydeepw.pokemondirectory.di.DaggerAppComponent

class MyApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        // Dagger%COMPONENT_NAME%
        appComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .build()

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerAppComponent.create();
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}
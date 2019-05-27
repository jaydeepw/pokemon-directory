package com.github.jaydeepw.pokemondirectory.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.jaydeepw.pokemondirectory.models.datasource.network.client.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule// Constructor needs one parameter to instantiate.
(private var baseUrl: String) {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application):
            SharedPreferences {
        // Application reference must come from AppModule.class
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logging)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()

        return retrofit
    }

    @Provides
    @Singleton
    internal fun providesApiClient(retrofit: Retrofit): ApiInterface {
        return retrofit.create<ApiInterface>(ApiInterface::class.java)
    }
}
package com.example.museleecopy.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.museleecopy.MuseleeApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideApplication(app: MuseleeApplication): Application = app

    @Provides
    @JvmStatic
    @Singleton
    fun providesConnectivityManager(app: Application): ConnectivityManager =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

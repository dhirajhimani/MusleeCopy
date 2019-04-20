package com.example.topartists.di

import com.example.topartists.view.TopArtistsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TopArtistsModule {

    @ContributesAndroidInjector(
        modules = [NetworkModule::class]
    )
    abstract fun bindTopArtistsFragment(): TopArtistsFragment

}

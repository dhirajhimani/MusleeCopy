package com.example.topartists.di

import com.example.topartists.view.TopArtistsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [NetworkModule::class]
)
abstract class TopArtistsModule {

    @ContributesAndroidInjector
    abstract fun bindTopArtistsFragment(): TopArtistsFragment

}

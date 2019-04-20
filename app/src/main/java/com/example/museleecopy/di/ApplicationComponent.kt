package com.example.museleecopy.di

import com.example.museleecopy.MuseleeApplication
import com.example.topartists.di.TopArtistsModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        TopArtistsModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MuseleeApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MuseleeApplication>()
}

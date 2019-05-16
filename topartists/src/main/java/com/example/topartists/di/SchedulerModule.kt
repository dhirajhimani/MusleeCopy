package com.example.topartists.di

import com.example.core.providers.UpdateScheduler
import com.example.topartists.entities.Artist
import com.example.topartists.scheduler.TopArtistsScheduler
import dagger.Module
import dagger.Provides

@Module
object SchedulerModule {

    @Provides
    @JvmStatic
    fun providesScheduler(): UpdateScheduler<Artist> =
        TopArtistsScheduler()

}

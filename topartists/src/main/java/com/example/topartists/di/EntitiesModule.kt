package com.example.topartists.di

import com.example.core.providers.DataPersister
import com.example.core.providers.DataProvider
import com.example.topartists.entities.Artist
import com.example.topartists.entities.TopArtistsRepository
import com.example.topartists.entities.TopArtistsState
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object EntitiesModule {

    @Provides
    @Named(TopArtistsModule.ENTITIES)
    @JvmStatic
    internal fun providesTopArtistsRepository(
        persistence: DataPersister<List<Artist>>,
        @Named(TopArtistsModule.NETWORK) networkProvider: DataProvider<TopArtistsState>
    ): DataProvider<TopArtistsState> = TopArtistsRepository(persistence, networkProvider)
}

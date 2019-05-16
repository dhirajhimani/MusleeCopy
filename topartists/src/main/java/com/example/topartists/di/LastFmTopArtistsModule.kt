package com.example.topartists.di

import com.example.core.providers.DataMapper
import com.example.core.providers.DataProvider
import com.example.topartists.entities.Artist
import com.example.topartists.entities.TopArtistsState
import com.example.topartists.net.LastFmArtists
import com.example.topartists.net.LastFmArtistsMapper
import com.example.topartists.net.LastFmTopArtistsApi
import com.example.topartists.net.LastFmTopArtistsProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object LastFmTopArtistsModule {

    @Provides
    @Named(TopArtistsModule.NETWORK)
    @JvmStatic
    fun providesTopArtistsProvider(
        lastFmTopArtistsApi: LastFmTopArtistsApi,
        mapper: DataMapper<Pair<LastFmArtists, Long>, List<Artist>>
    ): DataProvider<TopArtistsState> =
        LastFmTopArtistsProvider(
            lastFmTopArtistsApi,
            mapper
        )

    @Provides
    @JvmStatic
    fun providesLastFmMapper(): DataMapper<Pair<LastFmArtists, Long>, List<Artist>> =
        LastFmArtistsMapper()
}

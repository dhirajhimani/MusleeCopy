package com.example.topartists.di

import com.example.core.app.ConnectivityChecker
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

@Module
object LastFmTopArtistsRepositoryModule {

    @Provides
    @JvmStatic
    fun providesTopArtistsProvider(
        lastFmTopArtistsApi: LastFmTopArtistsApi,
        connectivityChecker: ConnectivityChecker,
        mapper: DataMapper<LastFmArtists, List<Artist>>
    ): DataProvider<TopArtistsState> =
        LastFmTopArtistsProvider(
            lastFmTopArtistsApi,
            connectivityChecker,
            mapper
        )

    @Provides
    @JvmStatic
    fun providesLastFmMapper(): DataMapper<LastFmArtists, List<Artist>> =
        LastFmArtistsMapper()
}

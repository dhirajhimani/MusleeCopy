package com.example.topartists.di

import android.app.Application
import androidx.room.Room
import com.example.core.providers.DataMapper
import com.example.core.providers.DataPersister
import com.example.topartists.database.DatabaseTopArtistsMapper
import com.example.topartists.database.DatabaseTopArtistsPersister
import com.example.topartists.database.DbArtist
import com.example.topartists.database.DbImage
import com.example.topartists.database.TopArtistsDao
import com.example.topartists.database.TopArtistsDatabase
import com.example.topartists.entities.Artist
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    @JvmStatic
    internal fun providesDatabase(context: Application): TopArtistsDatabase =
        Room.databaseBuilder(context, TopArtistsDatabase::class.java, "top-artists").build()

    @Provides
    @JvmStatic
    internal fun providesTopArtistsDao(database: TopArtistsDatabase): TopArtistsDao =
        database.topArtistsDao()

    @Provides
    @JvmStatic
    internal fun providesTopArtistsMapper():
            DataMapper<Triple<Int, Artist, Long>, Pair<DbArtist, Collection<DbImage>>> =
        DatabaseTopArtistsMapper()

    @Provides
    @JvmStatic
    internal fun providesDatabasePersister(
        dao: TopArtistsDao,
        mapper: DataMapper<Triple<Int, Artist, Long>, Pair<DbArtist, Collection<DbImage>>>
    ): DataPersister<List<Artist>> =
        DatabaseTopArtistsPersister(dao, mapper)
}

package com.example.topartists.net

import com.example.core.providers.DataMapper
import com.example.topartists.entities.Artist.ImageSize
import com.example.topartists.entities.Artist

class LastFmArtistsMapper : DataMapper<LastFmArtists, List<Artist>> {

    override fun map(source: LastFmArtists): List<Artist> =
        source.artists.artist.map { artist ->
            Artist(artist.name, artist.normalisedImages())
        }

    private fun LastFmArtist.normalisedImages() =
        images.map { it.size.toImageSize() to it.url }.toMap()

    private fun String.toImageSize(): ImageSize =
        when (this) {
            "small" -> ImageSize.SMALL
            "medium" -> ImageSize.MEDIUM
            "large" -> ImageSize.LARGE
            "extralarge" -> ImageSize.EXTRA_LARGE
            else -> ImageSize.UNKNOWN
        }

}

package com.example.topartists.net

import com.example.core.providers.DataMapper
import com.example.topartists.entities.Artist.ImageSize
import com.example.topartists.entities.Artist

class LastFmArtistsMapper : DataMapper<Pair<LastFmArtists, Long>, List<Artist>> {

    override fun encode(source: Pair<LastFmArtists, Long>): List<Artist> {
        val (lastFmArtists, expiry) = source
        return lastFmArtists.artists.artist.map { artist ->
            Artist(artist.name, artist.normalisedImages(), expiry)
        }
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

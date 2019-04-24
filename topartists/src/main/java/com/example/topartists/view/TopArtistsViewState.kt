package com.example.topartists.view

import com.example.topartists.entities.Artist

sealed class TopArtistsViewState {

    object InProgress : TopArtistsViewState()

    class ShowTopArtists(val topArtists: List<Artist>) : TopArtistsViewState()

    class ShowError(val message: String) : TopArtistsViewState()
}

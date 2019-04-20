package com.example.topartists.net

import com.example.core.app.ConnectivityChecker
import com.example.core.providers.DataMapper
import com.example.core.providers.DataProvider
import com.example.topartists.entities.Artist
import com.example.topartists.entities.TopArtistsState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LastFmTopArtistsProvider(
    private val topArtistsApi: LastFmTopArtistsApi,
    private val connectivityChecker: ConnectivityChecker,
    private val mapper: DataMapper<LastFmArtists, List<Artist>>
) : DataProvider<TopArtistsState> {

    override fun requestData(callback: (topArtists: TopArtistsState) -> Unit) {
        if (!connectivityChecker.isConnected) {
            callback(TopArtistsState.Error("No network connectivity"))
            return
        }
        callback(TopArtistsState.Loading)
        topArtistsApi.getTopArtists().enqueue(object : Callback<LastFmArtists> {
            override fun onFailure(call: Call<LastFmArtists>, t: Throwable) {
                callback(TopArtistsState.Error(t.localizedMessage))
            }

            override fun onResponse(call: Call<LastFmArtists>, response: Response<LastFmArtists>) {
                response.body()?.also { topArtists ->
                    callback(TopArtistsState.Success(mapper.map(topArtists)))
                }
            }
        })
    }
}

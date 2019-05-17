package com.example.topartists.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.connectivity.ConnectivityLiveData
import com.example.core.providers.DataProvider
import com.example.topartists.di.TopArtistsModule
import com.example.topartists.entities.TopArtistsState
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class TopArtistsViewModel @Inject constructor
    (
    @Named(TopArtistsModule.ENTITIES)
    private val topArtistsProvider: DataProvider<TopArtistsState>,
    val connectivityLiveData: ConnectivityLiveData
    ) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val mutableLiveData: MutableLiveData<TopArtistsViewState> = MutableLiveData()
    val topArtistsViewState: LiveData<TopArtistsViewState>
        get() = mutableLiveData

    init {
        load()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun load() = launch {
        withContext(Dispatchers.IO) {
            topArtistsProvider.requestData { artistsState ->
                update(artistsState)
            }
        }
    }

    private fun update(artistsState: TopArtistsState) = launch {
        withContext(Dispatchers.Main) {
            mutableLiveData.value = when (artistsState) {
                TopArtistsState.Loading -> TopArtistsViewState.InProgress
                is TopArtistsState.Error -> TopArtistsViewState.ShowError(artistsState.message)
                is TopArtistsState.Success -> TopArtistsViewState.ShowTopArtists(artistsState.artists)
            }
        }
    }
}
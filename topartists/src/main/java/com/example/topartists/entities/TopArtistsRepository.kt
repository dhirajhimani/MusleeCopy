package com.example.topartists.entities

import com.example.core.providers.DataPersister
import com.example.core.providers.DataProvider
import com.example.core.providers.UpdateScheduler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopArtistsRepository(
    private val persister: DataPersister<List<Artist>>,
    private val provider: DataProvider<TopArtistsState>,
    private val scheduler: UpdateScheduler<Artist>
) : DataProvider<TopArtistsState> {

    override fun requestData(callback: (item: TopArtistsState) -> Unit) =
        persister.requestData { artists ->
            if (artists.isEmpty()) {
                provider.requestData { state ->
                    if (state is TopArtistsState.Success) {
                        GlobalScope.launch(Dispatchers.IO) {
                            persister.persistData(state.artists)
                        }
                        scheduler.scheduleUpdate(state.artists)
                    }
                    callback(state)
                }
            } else {
                callback(TopArtistsState.Success(artists))
            }
        }
}

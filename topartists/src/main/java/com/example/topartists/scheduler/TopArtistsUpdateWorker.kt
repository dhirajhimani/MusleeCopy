package com.example.topartists.scheduler

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.core.providers.DataPersister
import com.example.core.providers.DataProvider
import com.example.core.providers.UpdateScheduler
import com.example.topartists.di.TopArtistsModule
import com.example.topartists.entities.Artist
import com.example.topartists.entities.TopArtistsState
import com.stylingandroid.muselee.work.DaggerWorkerFactory
import javax.inject.Inject
import javax.inject.Named

class TopArtistsUpdateWorker(
    private val provider: DataProvider<TopArtistsState>,
    private val persister: DataPersister<List<Artist>>,
    private val scheduler: UpdateScheduler<Artist>,
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result =
        when(val state = provider.requestData()) {
            is TopArtistsState.Success -> {
                persister.persistData(state.artists)
                scheduler.scheduleUpdate(state.artists)
                Result.success()
            }
            is TopArtistsState.Error -> Result.retry()
            is TopArtistsState.Loading -> throw IllegalStateException("Unexpected Loading State")
        }

    class Factory @Inject constructor(
        @Named(TopArtistsModule.NETWORK) private val provider: DataProvider<TopArtistsState>,
        private val persister: DataPersister<List<Artist>>,
        private val scheduler: UpdateScheduler<Artist>
    ) : DaggerWorkerFactory.ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker =
            TopArtistsUpdateWorker(provider, persister, scheduler, appContext, params)
    }
}

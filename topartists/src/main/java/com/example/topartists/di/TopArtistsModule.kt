package com.example.topartists.di

import androidx.lifecycle.ViewModel
import com.example.core.di.BaseViewModule
import com.example.core.di.ViewModelKey
import com.example.core.di.WorkerKey
import com.example.core.work.DaggerWorkerFactory
import com.example.topartists.scheduler.TopArtistsUpdateWorker
import com.example.topartists.view.TopArtistsFragment
import com.example.topartists.view.TopArtistsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        EntitiesModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        BaseViewModule::class,
        SchedulerModule::class,
        LastFmTopArtistsModule::class
    ]
)
@Suppress("unused")
abstract class TopArtistsModule {

    companion object {
        const val ENTITIES = "ENTITIES"
        const val NETWORK = "NETWORK"
    }

    @ContributesAndroidInjector
    abstract fun bindTopArtistsFragment(): TopArtistsFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopArtistsViewModel::class)
    abstract fun bindChartsViewModel(viewModel: TopArtistsViewModel): ViewModel

    @Binds
    @IntoMap
    @WorkerKey(TopArtistsUpdateWorker::class)
    abstract fun bindTopArtistsUpdateWorker(factory: TopArtistsUpdateWorker.Factory):
            DaggerWorkerFactory.ChildWorkerFactory
}

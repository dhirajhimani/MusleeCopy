package com.example.museleecopy.di

import com.example.museleecopy.MuseleeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMuseleeActivity(): MuseleeActivity

}

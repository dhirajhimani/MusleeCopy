package com.example.museleecopy.di

import com.example.museleecopy.MuseleeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract fun bindMuseleeActivity(): MuseleeActivity
}

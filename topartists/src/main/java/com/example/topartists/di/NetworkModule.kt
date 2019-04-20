package com.example.topartists.di

import com.example.core.di.CoreNetworkModule
import dagger.Module
import dagger.Provides


// (includes = [CoreNetworkModule::class])
@Module
class NetworkModule {

    @Provides
    fun testString() = "Hello World!"
    
}

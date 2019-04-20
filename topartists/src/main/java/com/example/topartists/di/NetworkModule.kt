package com.example.topartists.di

import com.example.core.di.CoreNetworkModule
import com.example.topartists.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Named


@Module
    //(includes = [CoreNetworkModule::class])
class NetworkModule {

//    @Provides
//    @Named("API_KEY")
//    @JvmStatic
//    internal fun providesApiKey() =
//        Interceptor { chain ->
//            val newRequest = chain.request().let { request ->
//                val newUrl = request.url().newBuilder()
//                    .addQueryParameter("api_key", BuildConfig.LAST_FM_APIKEY)
//                    .build()
//                request.newBuilder()
//                    .url(newUrl)
//                    .build()
//            }
//            chain.proceed(newRequest)
//        }


    @Provides
    fun testString() = "Hello World!"
    
}

package com.example.topartists.di

import com.example.core.di.CoreNetworkModule
import com.example.topartists.BuildConfig
import com.example.topartists.net.LastFmTopArtistsApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [CoreNetworkModule::class])
object NetworkModule {

    @Provides
    @Named("API_KEY")
    @JvmStatic
    internal fun providesApiKey() =
        Interceptor { chain ->
            val newRequest = chain.request().let { request ->
                val newUrl = request.url().newBuilder()
                    .addQueryParameter("api_key", BuildConfig.LAST_FM_APIKEY)
                    .build()
                request.newBuilder()
                    .url(newUrl)
                    .build()
            }
            chain.proceed(newRequest)
        }

    @Provides
    @Named("JSON")
    @JvmStatic
    internal fun providesJson() =
        Interceptor { chain ->
            val newRequest = chain.request().let { request ->
                val newUrl = request.url().newBuilder()
                    .addQueryParameter("format", "json")
                    .build()
                request.newBuilder()
                    .url(newUrl)
                    .build()
            }
            chain.proceed(newRequest)
        }

    @Provides
    @JvmStatic
    internal fun providesOkHttpClient(
        builder: OkHttpClient.Builder,
        @Named("API_KEY") apiKeyInterceptor: Interceptor,
        @Named("JSON") jsonInterceptor: Interceptor
    ): OkHttpClient =
        builder.addInterceptor(apiKeyInterceptor)
            .addInterceptor(jsonInterceptor)
            .build()

    @Provides
    @Singleton
    @JvmStatic
    internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/2.0/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @JvmStatic
    internal fun providesLastFmTopArtistsApi(retrofit: Retrofit): LastFmTopArtistsApi =
        retrofit.create(LastFmTopArtistsApi::class.java)


    @Provides
    @JvmStatic
    fun testString() = "Hello World!"
    
}

package com.example.androiddevchallenge.di

import com.rooparsh.data.network.NetworkClient
import com.rooparsh.data.network.api.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkSource(): DogApi = NetworkClient.provideDogApi()

}
package com.example.androiddevchallenge.di

import com.rooparsh.data.network.api.DogApi
import com.rooparsh.data.repo.DogRepo
import com.rooparsh.data.repo.DogRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDogRepository(dogApi: DogApi): DogRepo = DogRepositoryImpl(dogApi)
}
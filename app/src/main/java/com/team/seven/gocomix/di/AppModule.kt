package com.team.seven.gocomix.di

import com.team.seven.gocomix.repo.ComixRepository
import com.team.seven.gocomix.repo.ComixRepositoryFakeImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideComixRepository(): ComixRepository {
        return ComixRepositoryFakeImpl()
    }
}

package com.team.seven.gocomix.di

import com.google.firebase.auth.FirebaseAuth
import com.team.seven.gocomix.repo.ComixRepository
import com.team.seven.gocomix.repo.net.ComixNetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideComixRepository(): ComixRepository {
        return ComixNetRepository()
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}

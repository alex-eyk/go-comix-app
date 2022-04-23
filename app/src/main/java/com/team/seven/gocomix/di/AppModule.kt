package com.team.seven.gocomix.di

import com.google.firebase.auth.FirebaseAuth
import com.team.seven.gocomix.repo.ComixRepository
import com.team.seven.gocomix.repo.net.ComixNetRepository
import com.team.seven.gocomix.repo.net.ComixService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val COMICS_SERVER_URL = "http://185.46.10.196:8080"

    @Singleton
    @Provides
    fun provideComixService(retrofit: Retrofit): ComixService {
        return retrofit.create(ComixService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COMICS_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideComicRepository(comixService: ComixService): ComixRepository {
        return ComixNetRepository(comixService)
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}

package com.team.seven.gocomix.di

import com.google.firebase.auth.FirebaseAuth
import com.team.seven.gocomix.model.Constants
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

    @Singleton
    @Provides
    fun provideComixService(retrofit: Retrofit): ComixService {
        return retrofit.create(ComixService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
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

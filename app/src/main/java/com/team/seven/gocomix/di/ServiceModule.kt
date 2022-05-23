package com.team.seven.gocomix.di

import com.team.seven.gocomix.domaim.service.LocalRecognizeService
import com.team.seven.gocomix.domaim.service.LocalTranslateService
import com.team.seven.gocomix.domaim.service.RecognizeService
import com.team.seven.gocomix.domaim.service.TranslateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideRecognizeService(): RecognizeService {
        return LocalRecognizeService()
    }

    @Singleton
    @Provides
    fun provideTranslateService(): TranslateService {
        return LocalTranslateService()
    }
}

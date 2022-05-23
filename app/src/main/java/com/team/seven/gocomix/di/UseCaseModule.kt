package com.team.seven.gocomix.di

import com.team.seven.gocomix.domaim.interactor.TranslateRecognizeUseCase
import com.team.seven.gocomix.domaim.interactor.TranslateRecognizeUseCaseImpl
import com.team.seven.gocomix.domaim.service.RecognizeService
import com.team.seven.gocomix.domaim.service.TranslateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideTranslateRecognizeUseCase(
        recognizeService: RecognizeService,
        translateService: TranslateService
    ): TranslateRecognizeUseCase {
        return TranslateRecognizeUseCaseImpl(recognizeService, translateService)
    }
}

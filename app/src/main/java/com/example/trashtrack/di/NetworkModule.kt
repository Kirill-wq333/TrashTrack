package com.example.trashtrack.di

import com.example.trashtrack.ui.feature.user.introduction.viewmodel.repository.IntroductionRepository
import com.example.trashtrack.ui.feature.user.introduction.viewmodel.repository.IntroductionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindIntroductionRepository(
        impl: IntroductionRepositoryImpl
    ): IntroductionRepository
}
package com.payment.myapplication.di

import com.payment.myapplication.domain.repository.Repository
import com.payment.myapplication.domain.useCase.GetBanksUseCase
import com.payment.myapplication.domain.useCase.GetFeeUseCase
import com.payment.myapplication.domain.useCase.GetPaymentTypeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class UseCaseModule {

    @Provides
    fun providesGetPaymentTypeUseCase(
        repository: Repository
    ): GetPaymentTypeUseCase = GetPaymentTypeUseCase(repository)

    @Provides
    fun providesGetBanksUseCase(
        repository: Repository
    ): GetBanksUseCase = GetBanksUseCase(repository)

    @Provides
    fun providesGetFeesUseCase(
        repository: Repository
    ): GetFeeUseCase = GetFeeUseCase(repository)

}
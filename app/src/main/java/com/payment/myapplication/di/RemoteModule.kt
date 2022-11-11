package com.payment.myapplication.di

import com.payment.myapplication.data.remote.RemoteImpl
import com.payment.myapplication.data.retrofit.ApiService
import com.payment.myapplication.data.source.Remote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideRemote(apiService: ApiService): Remote {
        return RemoteImpl(apiService)
    }

}
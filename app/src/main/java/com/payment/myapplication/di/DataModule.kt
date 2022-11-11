package com.payment.myapplication.di

import com.payment.myapplication.data.DataRepository
import com.payment.myapplication.data.source.Remote
import com.payment.myapplication.data.sourceFactory.DataSourceFactory
import com.payment.myapplication.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(sourceFactory: DataSourceFactory): Repository {
        return DataRepository(sourceFactory)
    }

    @Provides
    @Singleton
    fun provideSourceFactory(remote: Remote): DataSourceFactory {
        return DataSourceFactory(remote)
    }

}
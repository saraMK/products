package com.test.products.di

import com.test.products.data.repo.DataRepositoryImpl
import com.test.products.domain.repo.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRepo(repo: DataRepositoryImpl): DataRepository = repo


    @Singleton
    @Provides
    fun getDispatcher(): CoroutineDispatcher = Dispatchers.IO



}
package com.example.shiftlab.di

import android.content.Context
import com.example.shiftlab.data.local.UserStorage
import com.example.shiftlab.data.local.UserStorageImpl
import com.example.shiftlab.data.repository.UserRepositoryImpl
import com.example.shiftlab.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideUserStorage(@ApplicationContext context: Context): UserStorage {
        return UserStorageImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }
}
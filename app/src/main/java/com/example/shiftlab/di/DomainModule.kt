package com.example.shiftlab.di

import com.example.shiftlab.domain.repository.UserRepository
import com.example.shiftlab.domain.usecase.GetUserUseCase
import com.example.shiftlab.domain.usecase.SaveUserUseCase
import com.example.shiftlab.domain.usecase.ValidateNameUseCase
import com.example.shiftlab.domain.usecase.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideValidateNameUseCase() = ValidateNameUseCase()

    @Provides
    fun provideValidatePasswordUseCase() = ValidatePasswordUseCase()

    @Provides
    fun provideGetUserNameUseCase(repository: UserRepository): GetUserUseCase {
        return GetUserUseCase(repository = repository)
    }

    @Provides
    fun provideSaveUserNameUseCase(repository: UserRepository): SaveUserUseCase {
        return SaveUserUseCase(repository = repository)
    }
}
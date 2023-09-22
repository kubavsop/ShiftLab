package com.example.shiftlab.domain.usecase

import com.example.shiftlab.domain.model.User
import com.example.shiftlab.domain.repository.UserRepository

class SaveUserNameUseCase(private val repository: UserRepository) {
    operator fun invoke(user: User) {
        repository.saveUsername(user = user)
    }
}
package com.example.shiftlab.domain.usecase

import com.example.shiftlab.domain.model.Username
import com.example.shiftlab.domain.repository.UserRepository

class SaveUserNameUseCase(private val repository: UserRepository) {
    operator fun invoke(userName: Username) {
        repository.saveUsername(username = userName)
    }
}
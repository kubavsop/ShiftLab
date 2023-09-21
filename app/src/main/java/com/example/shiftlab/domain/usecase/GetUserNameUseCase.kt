package com.example.shiftlab.domain.usecase

import com.example.shiftlab.domain.model.Username
import com.example.shiftlab.domain.repository.UserRepository

class GetUserNameUseCase(private val repository: UserRepository) {
    operator fun invoke(): Username? {
        return repository.getUsername()
    }
}
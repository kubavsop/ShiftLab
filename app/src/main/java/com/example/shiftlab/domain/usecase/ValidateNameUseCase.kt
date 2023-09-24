package com.example.shiftlab.domain.usecase

class ValidateNameUseCase {
    private companion object {
        const val nameRegexPattern = "[a-zA-Z]{3,20}"
    }

    operator fun invoke(name: String): Boolean {
        return name.matches(nameRegexPattern.toRegex())
    }
}
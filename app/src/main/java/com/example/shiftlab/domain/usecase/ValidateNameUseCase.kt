package com.example.shiftlab.domain.usecase

class ValidateNameUseCase {
    operator fun invoke(name: String): Boolean {
        return name.matches(nameRegexPattern.toRegex())
    }

    private companion object {
        const val nameRegexPattern = "[a-zA-Z]{3,20}"
    }
}
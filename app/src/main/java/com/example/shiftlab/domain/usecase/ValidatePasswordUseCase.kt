package com.example.shiftlab.domain.usecase


class ValidatePasswordUseCase {
    private companion object {
        const val passwordRegexPattern = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}"
    }

    operator fun invoke(password: String): Boolean {
        return password.matches(passwordRegexPattern.toRegex())
    }
}
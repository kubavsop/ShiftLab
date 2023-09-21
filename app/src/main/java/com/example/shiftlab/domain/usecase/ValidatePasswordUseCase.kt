package com.example.shiftlab.domain.usecase


class ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean {
        return password.matches(passwordRegexPattern.toRegex())
    }

    private companion object {
        const val passwordRegexPattern = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}"
    }
}
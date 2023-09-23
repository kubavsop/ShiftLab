package com.example.shiftlab.presentation.registration


sealed interface RegistrationState {
    object Registered : RegistrationState
    object ValidatedData : RegistrationState
    data class Content(val birthday: String) : RegistrationState
    data class Error(
        val firstNameError: Int? = null,
        val lastNameError: Int? = null,
        val confirmPasswordError: Int? = null,
        val passwordError: Int? = null
    ) : RegistrationState
}
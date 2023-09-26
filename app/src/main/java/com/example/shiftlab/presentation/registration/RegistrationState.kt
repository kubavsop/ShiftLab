package com.example.shiftlab.presentation.registration


sealed interface RegistrationState {
    data class Registered(val isRegistered: Boolean) : RegistrationState
    data class ValidatedData(val isValidate: Boolean) : RegistrationState
}
package com.example.shiftlab.presentation.registration

sealed class RegistrationEvent {
    data class SaveUserEvent(
        val firstName: String,
        val lastName: String,
        val password: String,
        val birthday: String
    ) : RegistrationEvent()

    data class DateChangedEvent(
        val year: Int,
        val month: Int,
        val day: Int
    ) : RegistrationEvent()

    data class DataUpdatedEvent(
        val firstName: String,
        val lastName: String,
        val password: String,
        val confirmPassword: String
    ) : RegistrationEvent()
}

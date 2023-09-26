package com.example.shiftlab.presentation.registration

data class ErrorState(
    val firstNameError: Int? = null,
    val lastNameError: Int? = null,
    val confirmPasswordError: Int? = null,
    val passwordError: Int? = null
)
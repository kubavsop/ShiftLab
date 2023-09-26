package com.example.shiftlab.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shiftlab.R
import com.example.shiftlab.domain.model.User
import com.example.shiftlab.domain.usecase.FormatDateUseCase
import com.example.shiftlab.domain.usecase.GetUserUseCase
import com.example.shiftlab.domain.usecase.SaveUserUseCase
import com.example.shiftlab.domain.usecase.ValidateNameUseCase
import com.example.shiftlab.domain.usecase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateNameUseCase: ValidateNameUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val saveUserNameUseCase: SaveUserUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    getUserNameUseCase: GetUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<RegistrationState>()
    val state: LiveData<RegistrationState> = _state

    private companion object {
        const val DATE_OF_BIRTH = "Date of birth is "
    }

    init {
        if (getUserNameUseCase() != null) {
            _state.value = RegistrationState.Registered(isRegistered = true)
        }
    }

    fun send(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.DataUpdatedEvent -> registrationDataUpdated(
                event.firstName,
                event.lastName,
                event.password,
                event.confirmPassword
            )

            is RegistrationEvent.DateChangedEvent -> dateChanged(event.year, event.month, event.day)
            is RegistrationEvent.SaveUserEvent -> saveUser(
                event.firstName,
                event.lastName,
                event.password,
                event.birthday
            )
        }
    }

    private fun registrationDataUpdated(
        firstName: String,
        lastName: String,
        password: String,
        confirmPassword: String
    ) {
        if (!validateNameUseCase(firstName)) {
            _state.value = RegistrationState.Error(firstNameError = R.string.invalid_name)
        } else if (!validateNameUseCase(lastName)) {
            _state.value = RegistrationState.Error(lastNameError = R.string.invalid_name)
        } else if (!validatePasswordUseCase(password)) {
            _state.value = RegistrationState.Error(passwordError = R.string.invalid_password)
        } else if (password != confirmPassword) {
            _state.value =
                RegistrationState.Error(confirmPasswordError = R.string.invalid_confirm_password)
        } else {
            _state.value = RegistrationState.ValidatedData
        }
    }

    private fun dateChanged(year: Int, month: Int, day: Int) {
        _state.value = RegistrationState.Content(birthday = DATE_OF_BIRTH + formatDateUseCase(year, month, day))
    }

    private fun saveUser(
        firstName: String,
        lastName: String,
        password: String,
        birthday: String
    ) {
        val user = User(
            firstName = firstName,
            lastName = lastName,
            password = password,
            birthday = birthday
        )
        saveUserNameUseCase(user = user)
        _state.value = RegistrationState.Registered(isRegistered = false)
    }
}
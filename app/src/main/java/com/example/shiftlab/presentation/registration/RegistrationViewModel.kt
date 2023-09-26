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
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateNameUseCase: ValidateNameUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val saveUserNameUseCase: SaveUserUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    getUserNameUseCase: GetUserUseCase
) : ViewModel() {

    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState> = _registrationState

    private val _errorState = MutableLiveData<ErrorState>()
    val errorState: LiveData<ErrorState> = _errorState

    private val _birthday = MutableLiveData(INITIAL_DATE)
    val birthday: LiveData<String> = _birthday


    init {
        if (getUserNameUseCase() != null) {
            _registrationState.value = RegistrationState.Registered(isRegistered = true)
        }
    }

    private companion object {
        const val INITIAL_DATE = "01.01.2020"
    }

    fun registrationDataUpdated(
        firstName: String,
        lastName: String,
        password: String,
        confirmPassword: String
    ) {
        _registrationState.value = RegistrationState.ValidatedData(isValidate = false)
        if (!validateNameUseCase(firstName)) {
            _errorState.value = ErrorState(firstNameError = R.string.invalid_name)
        } else if (!validateNameUseCase(lastName)) {
            _errorState.value = ErrorState(lastNameError = R.string.invalid_name)
        } else if (!validatePasswordUseCase(password)) {
            _errorState.value = ErrorState(passwordError = R.string.invalid_password)
        } else if (password != confirmPassword) {
            _errorState.value = ErrorState(confirmPasswordError = R.string.invalid_confirm_password)
        } else {
            _errorState.value = ErrorState()
            _registrationState.value = RegistrationState.ValidatedData(isValidate = true)
        }
    }

    fun dateChanged(year: Int, month: Int, day: Int) {
        _birthday.value = formatDateUseCase(year, month, day)
    }

    fun saveUser(
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
        _registrationState.value = RegistrationState.Registered(isRegistered = false)
    }
}
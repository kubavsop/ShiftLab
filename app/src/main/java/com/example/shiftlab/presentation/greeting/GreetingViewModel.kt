package com.example.shiftlab.presentation.greeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shiftlab.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<GreetingState>()
    val state: LiveData<GreetingState> = _state

    fun getUser() {
        _state.value = GreetingState(firstName = getUserUseCase()?.firstName ?: UNKNOWN )
    }

    private companion object {
        const val UNKNOWN = "unknown"
    }
}
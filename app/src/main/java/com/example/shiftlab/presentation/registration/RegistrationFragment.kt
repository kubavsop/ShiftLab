package com.example.shiftlab.presentation.registration

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.shiftlab.databinding.FragmentRegistrationBinding
import com.example.shiftlab.presentation.mainActivity
import java.util.Calendar

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by activityViewModels()


    private companion object {
        const val MIN_YEAR = 1880
        const val MAX_YEAR = 2020
        const val MIN_MONTH = 0
        const val MIN_DAY = 1
        const val MAX_MONTH = 11
        const val MAX_DAY = 31
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = binding.firstName
        val lastName = binding.lastName
        val password = binding.password
        val confirmPassword = binding.confirmPassword
        val birthday = binding.birthday

        viewModel.errorState.observe(viewLifecycleOwner, ::handleErrorState)
        viewModel.registrationState.observe(viewLifecycleOwner, ::handleRegistrationState)
        viewModel.birthday.observe(viewLifecycleOwner) { binding.birthday.text = it }


        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                viewModel.dateChanged(year, monthOfYear, dayOfMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        val minDateCalendar = Calendar.getInstance()
        minDateCalendar.set(MIN_YEAR, MIN_MONTH, MIN_DAY)
        datePickerDialog.datePicker.minDate = minDateCalendar.timeInMillis

        val maxDateCalendar = Calendar.getInstance()
        maxDateCalendar.set(MAX_YEAR, MAX_MONTH, MAX_DAY)
        datePickerDialog.datePicker.maxDate = maxDateCalendar.timeInMillis


        val afterTextChangedListener = getAfterTextChangedListener()
        firstName.addTextChangedListener(afterTextChangedListener)
        lastName.addTextChangedListener(afterTextChangedListener)
        password.addTextChangedListener(afterTextChangedListener)
        confirmPassword.addTextChangedListener(afterTextChangedListener)

        firstName.setClearFocusOnDoneClick()
        lastName.setClearFocusOnDoneClick()
        password.setClearFocusOnDoneClick()
        confirmPassword.setClearFocusOnDoneClick()

        birthday.setOnClickListener { datePickerDialog.show() }
        binding.register.setOnClickListener {
            viewModel.saveUser(
                firstName = firstName.text.toString(),
                lastName = lastName.text.toString(),
                password = password.text.toString(),
                birthday = birthday.text.toString()
            )
        }
    }

    private fun handleRegistrationState(state: RegistrationState) {
        when (state) {
            is RegistrationState.ValidatedData -> binding.register.isEnabled = state.isValidate
            is RegistrationState.Registered -> mainActivity.openGreeting(state.isRegistered)
        }
    }

    private fun handleErrorState(error: ErrorState) {
        binding.firstName.setError(error.firstNameError)
        binding.lastName.setError(error.lastNameError)
        binding.password.setError(error.passwordError)
        binding.confirmPassword.setError(error.confirmPasswordError)
    }

    private fun getAfterTextChangedListener() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            viewModel.registrationDataUpdated(
                firstName = binding.firstName.text.toString(),
                lastName = binding.lastName.text.toString(),
                password = binding.password.text.toString(),
                confirmPassword = binding.confirmPassword.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun EditText.setClearFocusOnDoneClick() {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            this.clearFocus()
        }
        false
    }
}

fun EditText.setError(id: Int?) {
    error = id?.let { context.getString(it) }
}
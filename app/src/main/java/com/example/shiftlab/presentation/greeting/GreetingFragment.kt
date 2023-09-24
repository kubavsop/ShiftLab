package com.example.shiftlab.presentation.greeting

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.shiftlab.R
import com.example.shiftlab.databinding.FragmentGreetingBinding
import com.example.shiftlab.databinding.FragmentRegistrationBinding


class GreetingFragment : Fragment() {
    private var _binding: FragmentGreetingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GreetingViewModel by activityViewModels()

    private companion object {
        const val GREETING = "Greeting"
        const val OK = "OK"
        const val GREETING_START = "Hello, "
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val greeting = AlertDialog.Builder(requireContext())
            .setTitle(GREETING)
            .setPositiveButton(OK) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        viewModel.state.observe(viewLifecycleOwner) {
            greeting.setMessage(GREETING_START + it.firstName)
        }

        binding.greetingButton.setOnClickListener {
            viewModel.getUser()
            greeting.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.shiftlab.presentation

import androidx.fragment.app.Fragment
import com.example.shiftlab.presentation.MainActivity

val Fragment.mainActivity: MainActivity
    get() = requireActivity() as MainActivity
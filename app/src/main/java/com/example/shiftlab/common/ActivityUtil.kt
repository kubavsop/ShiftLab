package com.example.shiftlab.common

import androidx.fragment.app.Fragment
import com.example.shiftlab.presentation.MainActivity

val Fragment.mainActivity: MainActivity
    get() = requireActivity() as MainActivity
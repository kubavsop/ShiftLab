package com.example.shiftlab.domain.repository

import com.example.shiftlab.domain.model.Username

interface UserRepository {
    fun saveUsername(username: Username)
    fun getUsername(): Username
}
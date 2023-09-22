package com.example.shiftlab.domain.repository

import com.example.shiftlab.domain.model.User

interface UserRepository {
    fun saveUsername(user: User)
    fun getUsername(): User?
}
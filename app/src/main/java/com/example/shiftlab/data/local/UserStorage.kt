package com.example.shiftlab.data.local


interface UserStorage {
    fun saveUsername(userName: UsernameEntity)
    fun getUsername(): UsernameEntity?
}
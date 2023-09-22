package com.example.shiftlab.data.local


interface UserStorage {
    fun saveUser(user: UserEntity)
    fun getUser(): UserEntity?
}
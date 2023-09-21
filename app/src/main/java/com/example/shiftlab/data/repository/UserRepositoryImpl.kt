package com.example.shiftlab.data.repository

import com.example.shiftlab.data.local.UserStorage
import com.example.shiftlab.data.local.UsernameEntity
import com.example.shiftlab.domain.model.Username
import com.example.shiftlab.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {
    override fun saveUsername(username: Username) {
        userStorage.saveUsername(username.toUsernameEntity())
    }

    override fun getUsername(): Username? {
        return userStorage.getUsername()?.toUsername()
    }
}


fun Username.toUsernameEntity() = UsernameEntity(firstName)
fun UsernameEntity.toUsername() = Username(firstName)

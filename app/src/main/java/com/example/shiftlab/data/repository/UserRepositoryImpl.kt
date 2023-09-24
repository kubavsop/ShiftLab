package com.example.shiftlab.data.repository

import com.example.shiftlab.data.local.UserEntity
import com.example.shiftlab.data.local.UserStorage
import com.example.shiftlab.domain.model.User
import com.example.shiftlab.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {
    override fun saveUsername(user: User) {
        userStorage.saveUser(user.toUserEntity())
    }

    override fun getUsername(): User? {
        return userStorage.getUser()?.toUser()
    }
}


fun UserEntity.toUser(): User {
    return User(
        firstName = firstName,
        lastName = lastName,
        password = password,
        birthday = birthday
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        firstName = firstName,
        lastName = lastName,
        password = password,
        birthday = birthday
    )
}

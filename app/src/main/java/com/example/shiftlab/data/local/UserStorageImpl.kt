package com.example.shiftlab.data.local

import android.content.Context
import com.google.gson.Gson

class UserStorageImpl(context: Context) : UserStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    private companion object {
        const val SHARED_PREF_NAME = "shared_pref"
        const val USER_KEY = "user"
    }

    override fun saveUser(user: UserEntity) {
        sharedPreferences.edit().putString(USER_KEY, gson.toJson(user))
            .apply()
    }

    override fun getUser(): UserEntity? {
        val userJson = sharedPreferences.getString(USER_KEY, null)
        return userJson?.let { gson.fromJson(it, UserEntity::class.java) }
    }

}
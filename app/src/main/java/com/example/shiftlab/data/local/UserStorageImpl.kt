package com.example.shiftlab.data.local

import android.content.Context

class UserStorageImpl(context: Context): UserStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun saveUsername(userName: UsernameEntity) {
        sharedPreferences.edit().putString(FIRST_NAME_KEY, userName.firstName)
            .apply()
    }

    override fun getUsername(): UsernameEntity? {
        val firstName = sharedPreferences.getString(FIRST_NAME_KEY, null)
        return firstName?.let { UsernameEntity(it) }
    }


    private companion object {
        const val SHARED_PREF_NAME = "shared_pref"
        const val FIRST_NAME_KEY = "name"
    }
}
package com.example.storyapp.data.login

import com.example.storyapp.data.Register.RegisterApiService
import com.example.storyapp.data.Register.RegisterResponse
import com.example.storyapp.data.pref.UserModel
import com.example.storyapp.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class LoginRepository private constructor(
    private val loginApiService: LoginApiService,
    private val userPreference: UserPreference
) {
    suspend fun loginRepo(email: String, password: String): LoginResponse {
        return loginApiService.login(email, password)
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object{
        @Volatile
        private var INSTANCE: LoginRepository? = null

        fun getInstance(userPreference: UserPreference): LoginRepository{
            return INSTANCE ?: synchronized(this){
                val instance = LoginRepository(LoginApiService.create(), userPreference)
                INSTANCE = instance
                instance
            }
        }
    }
}
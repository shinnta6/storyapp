package com.example.storyapp.data.Register

import com.example.storyapp.data.pref.UserModel
import com.example.storyapp.data.pref.UserPreference
import okhttp3.Response

class RegisterRepository private constructor(
    private val registerApiService: RegisterApiService
) {
    suspend fun registerRepo(name: String, email: String, password: String): RegisterResponse {
        return registerApiService.register(name, email, password)
    }
}
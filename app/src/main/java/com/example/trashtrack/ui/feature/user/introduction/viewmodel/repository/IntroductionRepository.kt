package com.example.trashtrack.ui.feature.user.introduction.viewmodel.repository

import com.example.trashtrack.ui.feature.user.introduction.viewmodel.IntroductionViewModel

interface IntroductionRepository {
    suspend fun getUserData(): Result<IntroductionViewModel.UserData>
    suspend fun saveUserData(email: String, passwordHash: String, name: String, phone: String): Result<Unit>
    suspend fun getAuthToken(): String?
    suspend fun saveAuthToken(token: String, expiresIn: Long): Result<Unit>
    suspend fun clearUserData(): Result<Unit>
    suspend fun validateUser(email: String, hashedPassword: String): Result<Boolean>
    suspend fun clearAuthData(): Result<Unit>
    suspend fun recordFailedAttempt(): Result<Unit>
    suspend fun isAccountLocked(): Boolean
    suspend fun resetAllSecurityData(): Result<Unit>
    suspend fun validateDataIntegrity(): Result<Boolean>
}
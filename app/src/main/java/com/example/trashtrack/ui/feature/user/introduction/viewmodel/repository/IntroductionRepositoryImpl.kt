package com.example.trashtrack.ui.feature.user.introduction.viewmodel.repository

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.trashtrack.ui.feature.user.introduction.viewmodel.IntroductionViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.KeyStore
import javax.crypto.AEADBadTagException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntroductionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : IntroductionRepository {

    private val masterKey by lazy {
        MasterKey.Builder(context, "master_key_alias")
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .setRequestStrongBoxBacked(true)
            .build()
    }

    private val sharedPrefs by lazy {
        EncryptedSharedPreferences.create(
            context,
            "secure_introduction_data",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override suspend fun validateUser(email: String, hashedPassword: String): Result<Boolean> {
        return try {
            val savedHash = sharedPrefs.getString(KEY_PASSWORD_HASH, null)

            val isValid = savedHash != null &&
                    email == sharedPrefs.getString(KEY_EMAIL, null) &&
                    hashedPassword == savedHash

            if (!isValid) recordFailedAttempt()

            Result.success(isValid)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearAuthData(): Result<Unit> {
        return try {
            sharedPrefs.edit {
                remove(KEY_EMAIL)
                remove(KEY_PASSWORD_HASH)
                remove(KEY_NAME)
                remove(KEY_PHONE)
                remove(KEY_TOKEN)
                remove("${KEY_TOKEN}_expiry")
                remove("login_attempts")
                remove("blocked_until")
                apply()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserData(): Result<IntroductionViewModel.UserData> {
        return try {
            val email = sharedPrefs.getString(KEY_EMAIL, null)
            val passwordHash = sharedPrefs.getString(KEY_PASSWORD_HASH, null)
            val name = sharedPrefs.getString(KEY_NAME, null)
            val phone = sharedPrefs.getString(KEY_PHONE, null)

            if (email != null && passwordHash != null) {
                Result.success(IntroductionViewModel.UserData(email, passwordHash, name, phone))
            } else {
                Result.failure(IllegalStateException("User data not found"))
            }
        } catch (e: SecurityException) {
            Result.failure(e)
        } catch (e: AEADBadTagException) {
            clearUserData()
            Result.failure(e)
        }
    }

    override suspend fun saveUserData(
        email: String,
        passwordHash: String,
        name: String,
        phone: String
    ): Result<Unit> {
        return try {
            sharedPrefs.edit {
                putString(KEY_EMAIL, email)
                putString(KEY_PASSWORD_HASH, passwordHash)
                putString(KEY_NAME, name)
                putString(KEY_PHONE, phone)
                apply()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAuthToken(): String? {
        return sharedPrefs.getString(KEY_TOKEN, null)
    }

    override suspend fun saveAuthToken(token: String, expiresIn: Long): Result<Unit> {
        return try {
            sharedPrefs.edit {
                putString(KEY_TOKEN, token)
                putLong("${KEY_TOKEN}_expiry", System.currentTimeMillis() + expiresIn)
                apply()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearUserData(): Result<Unit> {
        return try {
            sharedPrefs.edit {
                remove(KEY_EMAIL)
                remove(KEY_PASSWORD_HASH)
                remove(KEY_NAME)
                remove(KEY_PHONE)
                remove(KEY_TOKEN)
                remove("${KEY_TOKEN}_expiry")
                remove("login_attempts")
                remove("blocked_until")
                apply()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun recordFailedAttempt(): Result<Unit> {
        return try {
            val attempts = sharedPrefs.getInt("login_attempts", 0) + 1
            sharedPrefs.edit {
                putInt("login_attempts", attempts)
                if (attempts > MAX_LOGIN_ATTEMPTS) {
                    putLong("blocked_until", System.currentTimeMillis() + BLOCK_DURATION_MS)
                }
                apply()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isAccountLocked(): Boolean {
        return sharedPrefs.getLong("blocked_until", 0) > System.currentTimeMillis()
    }

    override suspend fun resetAllSecurityData(): Result<Unit> {
        return try {
            sharedPrefs.edit { clear() }

            KeyStore.getInstance("AndroidKeyStore").apply {
                load(null)
                deleteEntry("master_key_alias")
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun validateDataIntegrity(): Result<Boolean> {
        return try {
            // Просто пытаемся прочитать все данные
            sharedPrefs.getAll()
            Result.success(true)
        } catch (e: Exception) {
            Result.success(false)
        }
    }

    companion object {
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PASSWORD_HASH = "encrypted_password"
        private const val KEY_NAME = "user_name"
        private const val KEY_PHONE = "user_phone"
        private const val KEY_TOKEN = "auth_token"
        private const val MAX_LOGIN_ATTEMPTS = 5
        private const val BLOCK_DURATION_MS = 300_000L // 5 минут
    }

}
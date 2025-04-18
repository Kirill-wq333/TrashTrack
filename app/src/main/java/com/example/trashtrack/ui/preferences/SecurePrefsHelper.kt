package com.example.trashtrack.ui.preferences

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.MessageDigest

class SecurePrefsHelper (context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "secure_user_data",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Сохранение всех данных пользователя
    fun saveUserData(
        email: String,
        password: String,
        name: String,
        phone: String
    ) {
        sharedPrefs.edit {
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD_HASH, password.hashPassword())
            putString(KEY_NAME, name)
            putString(KEY_PHONE, phone)
            apply()
        }
    }

    // Получение всех данных пользователя
    fun getUserData(): UserData? {
        return try {
            UserData(
                email = sharedPrefs.getString(KEY_EMAIL, null),
                passwordHash = sharedPrefs.getString(KEY_PASSWORD_HASH, null),
                name = sharedPrefs.getString(KEY_NAME, null),
                phone = sharedPrefs.getString(KEY_PHONE, null)
            )
        } catch (e: Exception) {
            null
        }
    }

    // Индивидуальные методы получения данных
    fun getUserEmail(): String? = sharedPrefs.getString(KEY_EMAIL, null)
    fun getUserPasswordHash(): String? = sharedPrefs.getString(KEY_PASSWORD_HASH, null)
    fun getUserName(): String? = sharedPrefs.getString(KEY_NAME, null)
    fun getUserPhone(): String? = sharedPrefs.getString(KEY_PHONE, null)

    // Очистка всех данных
    fun clearUserData() {
        sharedPrefs.edit {
            clear()
            apply()
        }
    }

    // Проверка наличия сохраненных данных
    fun hasUserData(): Boolean {
        return sharedPrefs.contains(KEY_EMAIL) &&
                sharedPrefs.contains(KEY_PASSWORD_HASH)
    }

    private fun String.hashPassword(): String {
        return "salt_${this}_${length}".sha256()
    }

    // Вспомогательное расширение для хеширования (упрощенный пример)
    private fun String.sha256(): String {
        val bytes = this.toByteArray(Charsets.UTF_8)
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    // Модель данных пользователя
    data class UserData(
        val email: String?,
        val passwordHash: String?,
        val name: String?,
        val phone: String?
    )

    companion object {
        @Volatile
        private var instance: SecurePrefsHelper? = null

        fun getInstance(context: Context): SecurePrefsHelper {
            return instance ?: synchronized(this) {
                instance ?: SecurePrefsHelper(context.applicationContext).also {
                    instance = it
                }
            }
        }

        private const val KEY_EMAIL = "user_email"
        private const val KEY_PASSWORD_HASH = "encrypted_password"
        private const val KEY_NAME = "user_name"
        private const val KEY_PHONE = "user_phone"
    }
}
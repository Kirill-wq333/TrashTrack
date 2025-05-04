package com.example.trashtrack.ui.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.KeyStore
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.AEADBadTagException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

//class SecurePrefsHelper (context: Context) {
//    private val masterKey = MasterKey.Builder(context, "master_key_alias")
//        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
//        .setRequestStrongBoxBacked(true)
//        .build()
//
//    private val sharedPrefs = EncryptedSharedPreferences.create(
//        context,
//        "secure_user_data",
//        masterKey,
//        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//    )
//
//    fun isDataSaved(): Boolean {
//        return sharedPrefs.contains(KEY_EMAIL) &&
//                sharedPrefs.contains(KEY_PASSWORD_HASH) &&
//                sharedPrefs.contains(KEY_NAME) &&
//                sharedPrefs.contains(KEY_PHONE)
//    }
//
//    fun migrateFromLegacyPrefs(legacyPrefs: SharedPreferences) {
//        if (!isDataSaved() && legacyPrefs.contains("old_email_key")) {
//            saveUserData(
//                email = legacyPrefs.getString("old_email_key", "") ?: "",
//                password = legacyPrefs.getString("old_pass_key", "") ?: "",
//                name = legacyPrefs.getString("old_name_key", "") ?: "",
//                phone = legacyPrefs.getString("old_phone_key", "") ?: ""
//            )
//            legacyPrefs.edit().clear().apply()
//        }
//    }
//
//    // Метод для логирования всех сохраненных данных (только для отладки)
//    fun logAllData() {
//        Log.d("SecurePrefs", "Email: ${sharedPrefs.getString(KEY_EMAIL, null)}")
//        Log.d("SecurePrefs", "Name: ${sharedPrefs.getString(KEY_NAME, null)}")
//        Log.d("SecurePrefs", "Phone: ${sharedPrefs.getString(KEY_PHONE, null)}")
//        // Пароль хеширован, поэтому выводим только факт его наличия
//        Log.d("SecurePrefs", "Password saved: ${sharedPrefs.contains(KEY_PASSWORD_HASH)}")
//    }
//
//    // Сохранение всех данных пользователя
//    fun saveUserData(
//        email: String,
//        password: String,
//        name: String,
//        phone: String,
//        token: String = ""
//    ) {
//        sharedPrefs.edit {
//            putString(KEY_EMAIL, email)
//            putString(KEY_PASSWORD_HASH, password.hashPassword())
//            putString(KEY_NAME, name)
//            putString(KEY_PHONE, phone)
//            putString(KEY_TOKEN, token)
//            apply()
//        }
//    }
//
//    fun getUserData(): UserData? {
//        return try {
//            UserData(
//                email = sharedPrefs.getString(KEY_EMAIL, null),
//                passwordHash = sharedPrefs.getString(KEY_PASSWORD_HASH, null),
//                name = sharedPrefs.getString(KEY_NAME, null),
//                phone = sharedPrefs.getString(KEY_PHONE, null)
//            )
//        } catch (e: SecurityException) {
//            Log.e("SecurePrefs", "Security error reading data", e)
//            null
//        } catch (e: AEADBadTagException) {
//            Log.e("SecurePrefs", "Encryption corrupted", e)
//            clearUserData()
//            null
//        }
//    }
//
//    // Индивидуальные методы получения данных
//    fun getUserEmail(): String? = sharedPrefs.getString(KEY_EMAIL, null)
//    fun getUserPasswordHash(): String? = sharedPrefs.getString(KEY_PASSWORD_HASH, null)
//    fun getUserName(): String? = sharedPrefs.getString(KEY_NAME, null)
//    fun getUserPhone(): String? = sharedPrefs.getString(KEY_PHONE, null)
//    fun getAuthToken(): String? {
//        return sharedPrefs.getString(KEY_TOKEN, null)
//    }
//
//    // Очистка всех данных
//    fun clearUserData() {
//        sharedPrefs.edit {
//            remove(KEY_EMAIL)
//            remove(KEY_PASSWORD_HASH)
//            remove(KEY_NAME)
//            remove(KEY_PHONE)
//            apply()
//        }
//    }
//
//    fun validateDataIntegrity(): Boolean {
//        return try {
//            sharedPrefs.getAll()
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }
//
//    fun saveAuthToken(token: String, expiresIn: Long) {
//        sharedPrefs.edit {
//            putString(KEY_TOKEN, token)
//            putLong("${KEY_TOKEN}_expiry", System.currentTimeMillis() + expiresIn)
//        }
//    }
//
//    fun isValidToken(): Boolean {
//        val expiry = sharedPrefs.getLong("${KEY_TOKEN}_expiry", 0)
//        return expiry > System.currentTimeMillis()
//    }
//
//    private fun clear() {
//        sharedPrefs.edit {
//            clear()
//            apply()
//        }
//    }
//
//    fun recordFailedAttempt() {
//        val attempts = sharedPrefs.getInt("login_attempts", 0) + 1
//        sharedPrefs.edit { putInt("login_attempts", attempts) }
//
//        if (attempts > 5) {
//            sharedPrefs.edit { putLong("blocked_until", System.currentTimeMillis() + 300_000) } // 5 мин
//        }
//    }
//
//    fun isAccountLocked(): Boolean {
//        return sharedPrefs.getLong("blocked_until", 0) > System.currentTimeMillis()
//    }
//
//    fun deleteAccount() {
//        clearUserData()
//        clear()
//    }
//
//    fun resetAllSecurityData() {
//        sharedPrefs.edit {
//            clear()
//            apply()
//        }
//        try {
//            KeyStore.getInstance("AndroidKeyStore").deleteEntry("master_key_alias")
//        } catch (e: Exception) {
//            Log.e("SecurePrefs", "Error resetting keys", e)
//        }
//    }
//
//    // Проверка наличия сохраненных данных
//    fun hasUserData(): Boolean {
//        return sharedPrefs.contains(KEY_EMAIL) &&
//                sharedPrefs.contains(KEY_PASSWORD_HASH)
//    }
//
//    private fun String.hashPassword(): String {
//        val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }
//        val iterations = 10000
//        val keyLength = 256
//
//        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
//        val spec = PBEKeySpec(this.toCharArray(), salt, iterations, keyLength)
//        val hash = factory.generateSecret(spec).encoded
//
//        return "${Base64.encodeToString(salt, Base64.NO_WRAP)}:${Base64.encodeToString(hash, Base64.NO_WRAP)}"
//    }
//
//    // Модель данных пользователя
//    data class UserData(
//        val email: String?,
//        val passwordHash: String?,
//        val name: String?,
//        val phone: String?
//    )
//
//    companion object {
//        @Volatile
//        private var instance: SecurePrefsHelper? = null
//
//        fun getInstance(context: Context): SecurePrefsHelper {
//            return instance ?: synchronized(this) {
//                instance ?: SecurePrefsHelper(context.applicationContext).also {
//                    instance = it
//                }
//            }
//        }
//
//        private const val KEY_EMAIL = "user_email"
//        private const val KEY_PASSWORD_HASH = "encrypted_password"
//        private const val KEY_NAME = "user_name"
//        private const val KEY_PHONE = "user_phone"
//        private const val KEY_TOKEN = "auth_token"
//    }
//}
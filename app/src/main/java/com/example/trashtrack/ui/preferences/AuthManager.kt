package com.example.trashtrack.ui.preferences

import android.content.Context

//class AuthManager(context: Context) {
//
//    // region [Private Properties]
////    private val securePrefs = SecurePrefsHelper.getInstance(context)
//    // endregion
//
//    // region [Companion Object - Singleton]
//    companion object {
//        @Volatile
//        private var instance: AuthManager? = null
//
//        fun getInstance(context: Context): AuthManager {
//            return instance ?: synchronized(this) {
//                instance ?: AuthManager(context.applicationContext).also {
//                    instance = it
//                }
//            }
//        }
//    }
//    // endregion
//
//    // region [Public Methods]
//    suspend fun login(
//        email: String,
//        password: String,
//        name: String,
//        phone: String,
//        token: String
//    ): Boolean {
//        return try {
//            // Сохраняем данные
//            securePrefs.saveUserData(
//                email = email,
//                password = password, // В реальном приложении сохраняйте только токен
//                name = name,
//                phone = phone,
//                token = token // Сохраняем токен
//            )
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//
//    fun isUserLoggedIn(): Boolean {
//        return securePrefs.getUserEmail() != null &&
//                securePrefs.getAuthToken() != null
//    }
//
//    fun logout() {
//        // Очищаем локальные данные
//        securePrefs.clearUserData()
//    }
//
//    fun getCurrentUserEmail(): String? {
//        return securePrefs.getUserEmail()
//    }
//
//    fun getCurrentUserName(): String? {
//        return securePrefs.getUserName()
//    }
//
//    fun getCurrentUserPhone(): String? {
//        return securePrefs.getUserPhone()
//    }
//    // endregion
//
//    fun deleteAccount(): Boolean {
//        return try {
//            securePrefs.deleteAccount()
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//}
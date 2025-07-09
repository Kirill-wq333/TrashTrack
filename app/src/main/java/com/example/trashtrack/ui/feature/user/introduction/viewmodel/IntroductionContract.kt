package com.example.trashtrack.ui.feature.user.introduction.viewmodel

import com.example.trashtrack.core.viewmodel.ViewEffect
import com.example.trashtrack.core.viewmodel.ViewEvent
import com.example.trashtrack.core.viewmodel.ViewState

object IntroductionContract {
    sealed interface Event : ViewEvent {
        data object LoadUserData : Event
        data class SaveUserData(val email: String, val password: String, val name: String, val phone: String) : Event
        data class SaveAuthToken(val token: String, val expiresIn: Long) : Event
        data object ClearUserData : Event
        data class PhoneChanged(val phone: String) : Event
        data class EmailChanged(val email: String) : Event
        data class NameChanged(val name: String) : Event
        data class PasswordChanged(val password: String) : Event
        data class ConfirmPasswordChanged(val confirmPassword: String) : Event
        data object RecordFailedAttempt : Event
        data object ResetSecurityData : Event
        data object ValidateDataIntegrity : Event
//        data class ApplyPromoCode(val promoCode: String?) : Event
        data class Register(
            val email: String,
            val password: String,
            val name: String,
            val phone: String
        ) : Event
        data class AcceptTerms(val accepted: Boolean) : Event

        data class LoginEmailChanged(val email: String) : Event
        data class LoginPasswordChanged(val password: String) : Event
        data class Login(val email: String, val password: String) : Event

        data object ResetAuthState : Event
    }

    sealed interface State : ViewState {
        data object Idle : State
        data object Loading : State
        data object Saving : State
        data object Saved : State
        data object DataLoaded : State
        data class Error(val message: String?) : State
    }

    sealed interface Effect : ViewEffect {
        data object DataSaved : Effect
        data object DataCleared : Effect
        data object TokenSaved : Effect
        data object RegistrationSuccess : Effect
        data class RegistrationError(val message: String?) : Effect
        data class LoginError(val message: String?) : Effect
        data object LoginSuccess : Effect
        data object AuthStateReset : Effect
        data object AccountLocked : Effect
        data object SecurityDataReset : Effect
        data object DataIntegrityValid : Effect
        data object DataIntegrityInvalid : Effect
        data class ShowError(val message: String) : Effect
        data class PromoCodeApplied(val code: String) : Effect
    }
}
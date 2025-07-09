package com.example.trashtrack.ui.feature.user.introduction.viewmodel

import android.util.Base64
import androidx.lifecycle.viewModelScope
import com.example.trashtrack.core.viewmodel.BaseViewModel
import com.example.trashtrack.ui.feature.user.introduction.ui.IntroductionUiState
import com.example.trashtrack.ui.feature.user.introduction.viewmodel.repository.IntroductionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val introductionRepository: IntroductionRepository
) : BaseViewModel<IntroductionContract.Event, IntroductionContract.State, IntroductionContract.Effect>() {

    private val _userData = MutableStateFlow<UserData?>(null)
    val userData = _userData.asStateFlow()

    private val _uiState = MutableStateFlow(IntroductionUiState())
    val uiState: StateFlow<IntroductionUiState> = _uiState.asStateFlow()

    private val _state = MutableStateFlow<IntroductionContract.State>(IntroductionContract.State.Idle)
    val states: StateFlow<IntroductionContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<IntroductionContract.Effect>()
    val effects: SharedFlow<IntroductionContract.Effect> = _effect.asSharedFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    private val _authToken = MutableStateFlow<String?>(null)
    val authToken = _authToken.asStateFlow()

    private val _isAccountLocked = MutableStateFlow(false)
    val isAccountLocked = _isAccountLocked.asStateFlow()

    private val _registrationState = MutableStateFlow(RegistrationState())
    val registrationState: StateFlow<RegistrationState> = _registrationState

    override fun setInitialState(): IntroductionContract.State = IntroductionContract.State.Idle

    override fun handleEvent(event: IntroductionContract.Event) {
        when (event) {
            is IntroductionContract.Event.LoadUserData -> loadUserData()
            is IntroductionContract.Event.SaveUserData -> saveUserData(event.email, event.password, event.name, event.phone)
            is IntroductionContract.Event.SaveAuthToken -> saveAuthToken(event.token, event.expiresIn)
            is IntroductionContract.Event.ClearUserData -> clearUserData()
            is IntroductionContract.Event.RecordFailedAttempt -> recordFailedAttempt()
            is IntroductionContract.Event.ResetSecurityData -> resetSecurityData()
            is IntroductionContract.Event.ValidateDataIntegrity -> validateDataIntegrity()

            is IntroductionContract.Event.EmailChanged -> updateRegistrationState { it.copy(email = event.email) }
            is IntroductionContract.Event.PasswordChanged -> updateRegistrationState { it.copy(password = event.password) }
            is IntroductionContract.Event.ConfirmPasswordChanged -> updateRegistrationState { it.copy(confirmPassword = event.confirmPassword) }
            is IntroductionContract.Event.NameChanged -> updateRegistrationState { it.copy(name = event.name) }
            is IntroductionContract.Event.PhoneChanged -> updateRegistrationState { it.copy(phone = event.phone) }
            is IntroductionContract.Event.Register -> registerUser(event.email, event.password, event.name, event.phone)

            // Вход
            is IntroductionContract.Event.LoginEmailChanged -> updateLoginState { it.copy(email = event.email) }
            is IntroductionContract.Event.LoginPasswordChanged -> updateLoginState { it.copy(password = event.password) }
            is IntroductionContract.Event.Login -> loginUser(event.email, event.password)

            // Общие
            is IntroductionContract.Event.ResetAuthState -> resetAuthState()
            is IntroductionContract.Event.AcceptTerms -> updateRegistrationState { it.copy(isFormValid = event.accepted) }
        }
    }

    data class LoginState(
        val email: String = "",
        val password: String = "",
        val isFormValid: Boolean = false
    )

    private fun loadUserData() {
        viewModelScope.launch {
            setState(IntroductionContract.State.Loading)
            introductionRepository.getUserData()
                .onSuccess { data ->
                    _userData.value = data
                    _authToken.value = introductionRepository.getAuthToken()
                    setState(IntroductionContract.State.DataLoaded)
                }
                .onFailure {
                    setEffect { IntroductionContract.Effect.ShowError("Failed to load user data: ${it.message}") }
                    setState(IntroductionContract.State.Error("${it.message}"))
                }
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch(dispatcher) {
            _state.value = IntroductionContract.State.Loading
            introductionRepository.validateUser(email, password.hashPassword())
                .onSuccess { isValid ->
                    if (isValid) {
                        _effect.emit(IntroductionContract.Effect.LoginSuccess)
                        loadUserData()
                    } else {
                        _effect.emit(IntroductionContract.Effect.LoginError("Invalid credentials"))
                        introductionRepository.recordFailedAttempt()
                        checkAccountLock()
                    }
                }
                .onFailure {
                    _effect.emit(IntroductionContract.Effect.LoginError(it.message ?: "Login failed"))
                }
            _state.value = IntroductionContract.State.Idle
        }
    }

    private fun checkAccountLock() {
        viewModelScope.launch(dispatcher) {
            val isLocked = introductionRepository.isAccountLocked()
            if (isLocked) {
                _effect.emit(IntroductionContract.Effect.AccountLocked)
            }
        }
    }

    private fun saveUserData(email: String, password: String, name: String, phone: String) {
        viewModelScope.launch {
            setState(IntroductionContract.State.Saving)
            introductionRepository.saveUserData(
                email = email,
                passwordHash = password.hashPassword(),
                name = name,
                phone = phone
            )
                .onSuccess {
                    _userData.value = UserData(email, null, name, phone) // password hash not stored in memory
                    setState(IntroductionContract.State.Saved)
                    setEffect { IntroductionContract.Effect.DataSaved }
                }
                .onFailure {
                    setEffect { IntroductionContract.Effect.ShowError("Failed to save data: ${it.message}") }
                    setState(IntroductionContract.State.Error("${it.message}"))
                }
        }
    }

    private fun resetAuthState() {
        viewModelScope.launch(dispatcher) {
            introductionRepository.clearAuthData()
            _registrationState.value = RegistrationState()
            _loginState.value = LoginState()
            _effect.emit(IntroductionContract.Effect.AuthStateReset)
        }
    }

    private fun saveAuthToken(token: String, expiresIn: Long) {
        viewModelScope.launch {
            introductionRepository.saveAuthToken(token, expiresIn)
                .onSuccess {
                    _authToken.value = token
                    setEffect { IntroductionContract.Effect.TokenSaved }
                }
                .onFailure {
                    setEffect { IntroductionContract.Effect.ShowError("Failed to save token: ${it.message}") }
                }
        }
    }

    private inline fun updateRegistrationState(transform: (RegistrationState) -> RegistrationState) {
        _registrationState.update(transform)
    }

    private inline fun updateLoginState(transform: (LoginState) -> LoginState) {
        _loginState.update(transform)
    }

    private fun clearUserData() {
        viewModelScope.launch {
            introductionRepository.clearUserData()
                .onSuccess {
                    _userData.value = null
                    _authToken.value = null
                    setEffect { IntroductionContract.Effect.DataCleared }
                }
                .onFailure {
                    setEffect { IntroductionContract.Effect.ShowError("Failed to clear data: ${it.message}") }
                }
        }
    }

    private fun recordFailedAttempt() {
        viewModelScope.launch {
            introductionRepository.recordFailedAttempt()
                .onSuccess {
                    _isAccountLocked.value = introductionRepository.isAccountLocked()
                    if (_isAccountLocked.value) {
                        setEffect { IntroductionContract.Effect.AccountLocked }
                    }
                }
        }
    }

    private fun resetSecurityData() {
        viewModelScope.launch {
            introductionRepository.resetAllSecurityData()
                .onSuccess {
                    _userData.value = null
                    _authToken.value = null
                    _isAccountLocked.value = false
                    setEffect { IntroductionContract.Effect.SecurityDataReset }
                }
                .onFailure {
                    setEffect { IntroductionContract.Effect.ShowError("Failed to reset security: ${it.message}") }
                }
        }
    }

    private fun validateDataIntegrity() {
        viewModelScope.launch {
            introductionRepository.validateDataIntegrity()
                .onSuccess { isValid ->
                    setEffect {
                        if (isValid) {
                            IntroductionContract.Effect.DataIntegrityValid
                        } else {
                            IntroductionContract.Effect.DataIntegrityInvalid
                        }
                    }
                }
                .onFailure {
                    setEffect { IntroductionContract.Effect.ShowError("Validation error: ${it.message}") }
                }
        }
    }


    private fun String.hashPassword(): String {
        val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }
        val iterations = 10000
        val keyLength = 256

        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(this.toCharArray(), salt, iterations, keyLength)
        val hash = factory.generateSecret(spec).encoded

        return "${Base64.encodeToString(salt, Base64.NO_WRAP)}:${Base64.encodeToString(hash, Base64.NO_WRAP)}"
    }

    data class UserData(
        val email: String?,
        val passwordHash: String?,
        val name: String?,
        val phone: String?
    )

    data class RegistrationState(
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val name: String = "",
        val phone: String = "7",
        val passwordsMatch: Boolean = false,
        val isFormValid: Boolean = false
    )


    fun onEvent(event: IntroductionContract.Event) {
        when (event) {
            is IntroductionContract.Event.EmailChanged -> {
                _registrationState.update { it.copy(email = event.email) }
                validateForm()
            }
            is IntroductionContract.Event.PasswordChanged -> {
                _registrationState.update { it.copy(password = event.password) }
                validateForm()
            }
            is IntroductionContract.Event.Register -> {
                registerUser(event.email, event.password, event.name, event.phone)
            }
            else -> {}
        }
    }

    private fun validateForm() {
        val state = _registrationState.value
        val passwordsMatch = state.password == state.confirmPassword
        val isFormValid = passwordsMatch &&
                state.email.isNotBlank() &&
                state.password.isNotBlank() &&
                state.name.isNotBlank()

        _registrationState.update {
            it.copy(
                passwordsMatch = passwordsMatch,
                isFormValid = isFormValid
            )
        }
    }

    private fun registerUser(email: String, password: String, name: String, phone: String) {
        viewModelScope.launch {
            _state.value = IntroductionContract.State.Loading
            introductionRepository.saveUserData(email, password, name, phone)
                .onSuccess {
                    _effect.emit(IntroductionContract.Effect.RegistrationSuccess)
                }
                .onFailure {
                    _effect.emit(IntroductionContract.Effect.RegistrationError(it.message))
                }
            _state.value = IntroductionContract.State.Idle
        }
    }
}
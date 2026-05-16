package app.pasha.hackaton.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.error.ErrorReporter
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.network.model.LoginRequest
import app.pasha.hackaton.domain.auth.LoginUseCase
import app.pasha.hackaton.feature.dashboard.presentation.DashboardScreen
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel(
    private val navigator: Navigator,
    private val loginUseCase: LoginUseCase,
    private val errorReporter: ErrorReporter
) : ViewModel(), Stateful<LoginState> by statefulViewModel(LoginState()), KoinComponent {

    private val dashboardScreen: DashboardScreen by inject()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UpdateUsername -> {
                updateState { it.copy(username = intent.username) }
            }

            is LoginIntent.UpdatePassword -> {
                updateState { it.copy(password = intent.password) }
            }

            LoginIntent.Login -> login()
            LoginIntent.DismissError -> {
                // Now handled by ErrorReporter globally if desired, 
                // but we can keep it here if we want to clear local state too.
                updateState { it.copy(error = null) }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            try {
                updateState { it.copy(isLoading = true, error = null) }

                val result = loginUseCase(
                    LoginRequest(
                        username = state.value.username,
                        password = state.value.password
                    )
                )

                if (result.isSuccess) {
                    navigator.backStack.clear()
                    navigator.navigateTo(dashboardScreen)
                } else {
                    val message = result.exceptionOrNull()?.message ?: "Unknown error occurred"
                    errorReporter.reportError(message)
                }
                updateState { it.copy(username = "", password = "") }

            } finally {
                updateState { it.copy(isLoading = false) }
            }
        }
    }
}

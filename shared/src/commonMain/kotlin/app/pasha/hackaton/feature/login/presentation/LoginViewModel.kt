package app.pasha.hackaton.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.feature.home.presentation.HomeScreen
import kotlinx.coroutines.launch

class LoginViewModel(
    private val navigator: Navigator,
    private val homeScreen: HomeScreen,
) : ViewModel(), Stateful<LoginState> by statefulViewModel(LoginState()) {

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UpdateUsername -> {
                updateState { it.copy(username = intent.username) }
            }

            is LoginIntent.UpdatePassword -> {
                updateState { it.copy(password = intent.password) }
            }

            LoginIntent.Login -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            // Simulate network call
            navigator.navigateTo(homeScreen)
            updateState { it.copy(isLoading = false) }
        }
    }
}

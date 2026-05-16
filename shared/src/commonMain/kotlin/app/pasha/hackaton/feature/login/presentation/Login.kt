package app.pasha.hackaton.feature.login.presentation


data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)

sealed interface LoginIntent {
    data class UpdateUsername(val username: String) : LoginIntent
    data class UpdatePassword(val password: String) : LoginIntent
    data object Login : LoginIntent
}

package app.pasha.hackaton

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.ui.NavDisplay
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.feature.login.presentation.LoginScreen
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    val navigator = koinInject<Navigator>()
    val loginScreen = koinInject<LoginScreen>()

    LaunchedEffect(Unit) {
        if (navigator.backStack.isEmpty()) {
            navigator.navigateTo(loginScreen)
        }
    }

    MaterialTheme {
        if (navigator.backStack.isNotEmpty()) {
            NavDisplay(
                navigator.backStack,
                onBack = navigator::back,
                entryProvider = koinEntryProvider(),
            )
        }
    }
}

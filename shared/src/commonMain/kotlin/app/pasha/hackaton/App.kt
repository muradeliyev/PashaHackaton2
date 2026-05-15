package app.pasha.hackaton

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.ui.NavDisplay
import app.pasha.hackaton.core.navigation.Navigator
import org.koin.compose.getKoin
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val koin = getKoin()
        val navigator = koin.get<Navigator>()

        NavDisplay(
            navigator.backStack,
            onBack = navigator::back,
            entryProvider = koinEntryProvider()
        )
    }
}
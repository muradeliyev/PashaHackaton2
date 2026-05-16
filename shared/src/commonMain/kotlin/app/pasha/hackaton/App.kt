package app.pasha.hackaton

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.ui.NavDisplay
import androidx.compose.runtime.getValue
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.feature.forecast.presentation.ForecastScreen
import app.pasha.hackaton.feature.home.presentation.HomeScreen
import app.pasha.hackaton.feature.login.presentation.LoginScreen
import app.pasha.hackaton.feature.recommendations.presentation.RecommendationsScreen
import app.pasha.hackaton.feature.actions.presentation.AppliedActionsScreen
import app.pasha.hackaton.feature.analysis.presentation.BranchAnalysisScreen
import app.pasha.hackaton.core.error.ErrorReporter
import app.pasha.hackaton.ui.kit.component.Sidebar
import app.pasha.hackaton.ui.kit.component.ErrorDialog
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    val navigator = koinInject<Navigator>()
    val errorReporter = koinInject<ErrorReporter>()
    val loginScreen = koinInject<LoginScreen>()
    val homeScreen = koinInject<HomeScreen>()
    val forecastScreen = koinInject<ForecastScreen>()
    val recommendationsScreen = koinInject<RecommendationsScreen>()
    val appliedActionsScreen = koinInject<AppliedActionsScreen>()
    val branchAnalysisScreen = koinInject<BranchAnalysisScreen>()

    val globalError by errorReporter.errorState

    LaunchedEffect(Unit) {
        if (navigator.backStack.isEmpty()) {
            navigator.navigateTo(loginScreen)
        }
    }

    MaterialTheme {
        if (navigator.backStack.isNotEmpty()) {
            val currentScreen = navigator.backStack.last()
            val isSidebarVisible = currentScreen !is LoginScreen
            
            Row(modifier = Modifier.fillMaxSize()) {
                if (isSidebarVisible) {
                    val sidebarItems = listOf("Dashboard", "Forecast", "Recommendations", "Applied actions", "Branch analysis")
                    val selectedIndex = when (currentScreen) {
                        is HomeScreen -> 0
                        is ForecastScreen -> 1
                        is RecommendationsScreen -> 2
                        is AppliedActionsScreen -> 3
                        is BranchAnalysisScreen -> 4
                        else -> -1
                    }

                    Sidebar(
                        items = sidebarItems,
                        selectedIndex = selectedIndex,
                        onItemClick = { index ->
                            when (index) {
                                0 -> navigator.navigateTo(homeScreen)
                                1 -> navigator.navigateTo(forecastScreen)
                                2 -> navigator.navigateTo(recommendationsScreen)
                                3 -> navigator.navigateTo(appliedActionsScreen)
                                4 -> navigator.navigateTo(branchAnalysisScreen)
                            }
                        },
                        onLogout = {
                            navigator.back()
                        }
                    )
                }

                NavDisplay(
                    navigator.backStack,
                    onBack = navigator::back,
                    entryProvider = koinEntryProvider(),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        globalError?.let { message ->
            ErrorDialog(
                message = message,
                onDismiss = { errorReporter.clearError() }
            )
        }
    }
}

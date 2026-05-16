package app.pasha.hackaton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.ui.NavDisplay
import app.pasha.hackaton.core.error.ErrorReporter
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.feature.forecast.presentation.ForecastScreen
import app.pasha.hackaton.feature.dashboard.presentation.DashboardScreen
import app.pasha.hackaton.feature.login.presentation.LoginScreen
import app.pasha.hackaton.feature.recommendations.presentation.RecommendationsScreen
import app.pasha.hackaton.feature.actions.presentation.AppliedActionsScreen
import app.pasha.hackaton.feature.analysis.presentation.BranchAnalysisScreen
import app.pasha.hackaton.ui.kit.component.Sidebar
import app.pasha.hackaton.ui.kit.component.ErrorDialog
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI
import pashahackaton2.shared.generated.resources.Res
import pashahackaton2.shared.generated.resources.brand

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    val koin = getKoin()
    val navigator = koinInject<Navigator>()
    val errorReporter = koinInject<ErrorReporter>()

    val globalError by errorReporter.errorState

    LaunchedEffect(Unit) {
        if (navigator.backStack.isEmpty()) {
            navigator.navigateTo(koin.get<LoginScreen>())
        }
    }

    MaterialTheme {
        if (navigator.backStack.isNotEmpty()) {
            val currentScreen = navigator.backStack.last()
            val isSidebarVisible = currentScreen !is LoginScreen
            
            Row(
                modifier = Modifier
                    .fillMaxSize()
//                    .background(if (isSidebarVisible) Color(0xFFF7F6F6) else Color.White)
            ) {
                if (isSidebarVisible) {
                    val sidebarItems = listOf("Dashboard", "Forecast", "Recommendations", "Applied actions", "Branch analysis")
                    val selectedIndex = when (currentScreen) {
                        is DashboardScreen -> 0
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
                                0 -> navigator.navigateTo(koin.get<DashboardScreen>())
                                1 -> navigator.navigateTo(koin.get<ForecastScreen>())
                                2 -> navigator.navigateTo(koin.get<RecommendationsScreen>())
                                3 -> navigator.navigateTo(koin.get<AppliedActionsScreen>())
                                4 -> navigator.navigateTo(koin.get<BranchAnalysisScreen>())
                            }
                        },
                        onLogout = {
                            navigator.backStack.clear()
                            navigator.navigateTo(koin.get<LoginScreen>())
                        },
                        painter = painterResource(Res.drawable.brand),
                    )
                }

                NavDisplay(
                    navigator.backStack,
                    onBack = navigator::back,
                    entryProvider = koinEntryProvider(),
                    modifier = Modifier.weight(1f).fillMaxHeight()
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

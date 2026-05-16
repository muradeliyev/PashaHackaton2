package app.pasha.hackaton.feature.dashboard.presentation

import androidx.compose.runtime.Composable
import app.pasha.hackaton.core.navigation.Screen


class DashboardScreen(private val viewModel: DashboardViewModel) : Screen {

    @Composable
    override fun Content() {
        DashboardPage(viewModel = viewModel)
    }

}

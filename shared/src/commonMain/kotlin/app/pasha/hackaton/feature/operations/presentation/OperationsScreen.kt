package app.pasha.hackaton.feature.operations.presentation

import androidx.compose.runtime.Composable
import app.pasha.hackaton.core.navigation.Screen

class OperationsScreen(private val viewModel: OperationsViewModel) : Screen {

    @Composable
    override fun Content() {
        OperationsPage(viewModel)
    }
}

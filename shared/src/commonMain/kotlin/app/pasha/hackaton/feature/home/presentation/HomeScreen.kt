package app.pasha.hackaton.feature.home.presentation

import androidx.compose.runtime.Composable
import app.pasha.hackaton.core.navigation.Screen


class HomeScreen(private val viewModel: HomeViewModel) : Screen {

    @Composable
    override fun Content() {
        HomePage(viewModel = viewModel)
    }

}

package app.pasha.hackaton.feature.forecast.presentation

import androidx.compose.runtime.Composable
import app.pasha.hackaton.core.navigation.Screen

class ForecastScreen(private val viewModel: ForecastViewModel) : Screen {

    @Composable
    override fun Content() {
        ForecastPage(viewModel)
    }
}

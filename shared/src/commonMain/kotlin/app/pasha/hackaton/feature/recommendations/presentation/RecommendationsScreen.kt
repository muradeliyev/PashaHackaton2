package app.pasha.hackaton.feature.recommendations.presentation

import androidx.compose.runtime.Composable
import app.pasha.hackaton.core.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class RecommendationsScreen : Screen {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        RecommendationsPage(koinViewModel())
    }
}

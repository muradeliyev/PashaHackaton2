package app.pasha.hackaton.feature.analysis.presentation

import androidx.compose.runtime.Composable
import app.pasha.hackaton.core.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class BranchAnalysisScreen : Screen {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        BranchAnalysisPage(koinViewModel())
    }
}

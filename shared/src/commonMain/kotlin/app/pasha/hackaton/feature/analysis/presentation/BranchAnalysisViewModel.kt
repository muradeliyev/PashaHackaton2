package app.pasha.hackaton.feature.analysis.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import kotlinx.coroutines.launch
import app.pasha.hackaton.core.storage.UserRepository
import org.koin.core.component.KoinComponent

sealed interface BranchAnalysisState {
    data object Loading : BranchAnalysisState

    data class Ready(
        val userName: String = "",
        val selectedPeriod: String = "Q2 - 2026",
        val riskyBranches: List<BranchMetricItem> = listOf(
            BranchMetricItem("Sumgayit", 0.57f, "5.7%", Color(0xFFC4441F), Color(0xFFE5A592)),
            BranchMetricItem("Khatai", 0.30f, "3.0%", Color(0xFFD89919), Color(0xFFFBECC8)),
            BranchMetricItem("28 Mall", 0.25f, "2.5%", Color(0xFFD89919), Color(0xFFFBECC8)),
            BranchMetricItem("Yasamal", 0.18f, "1.8%", Color(0xFF2970DB), Color(0xFFE7F0FF))
        ),
        val salesOpportunities: List<BranchMetricItem> = listOf(
            BranchMetricItem("Sumgayit", 0.72f, "47 283₼", Color(0xFF2970DB), Color(0xFFE7F0FF)),
            BranchMetricItem("Khatai", 0.45f, "32 831₼", Color(0xFF2970DB), Color(0xFFE7F0FF)),
            BranchMetricItem("28 Mall", 0.34f, "18 283₼", Color(0xFF2970DB), Color(0xFFE7F0FF)),
            BranchMetricItem("Yasamal", 0.17f, "9 283₼", Color(0xFF2970DB), Color(0xFFE7F0FF))
        )
    ) : BranchAnalysisState
}

data class BranchMetricItem(
    val name: String,
    val progress: Float,
    val valueLabel: String,
    val primaryColor: Color,
    val secondaryColor: Color
)

class BranchAnalysisViewModel(
    private val navigator: Navigator,
    private val userRepository: UserRepository
) : ViewModel(), Stateful<BranchAnalysisState> by statefulViewModel(BranchAnalysisState.Loading), KoinComponent {

    init {
        loadData()
        observeUser()
    }

    private fun loadData() {
        viewModelScope.launch {
            updateState { BranchAnalysisState.Ready() }
        }
    }

    private fun observeUser() {
        viewModelScope.launch {
            userRepository.userName.collect { name ->
                updateState { state ->
                    when (state) {
                        BranchAnalysisState.Loading -> BranchAnalysisState.Loading
                        is BranchAnalysisState.Ready -> state.copy(userName = name)
                    }
                }
            }
        }
    }
}

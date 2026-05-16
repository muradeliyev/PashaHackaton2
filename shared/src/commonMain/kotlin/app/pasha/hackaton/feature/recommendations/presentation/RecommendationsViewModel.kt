package app.pasha.hackaton.feature.recommendations.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.error.ErrorReporter
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.storage.UserRepository
import app.pasha.hackaton.feature.forecast.presentation.ForecastScreen
import app.pasha.hackaton.feature.dashboard.presentation.DashboardScreen
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class RecommendationState(
    val userName: String = "",
    val sidebarItems: List<String> = listOf("Dashboard", "Forecast", "Recommendations", "Applied actions", "Branch analysis"),
    val selectedSidebarIndex: Int = 2,
    val totalImpactSales: String = "+ 32 150,",
    val totalImpactSalesFraction: String = "22 ₼",
    val wasteReduction: String = "- 1.41%",
    val recommendations: List<RecommendationItem> = listOf(
        RecommendationItem(
            id = "1",
            type = RecommendationType.DISCOUNT,
            title = "Evening discount - 19:00+",
            description = "Apply 15% discount on Vegetables in Sumgayit after 7pm; batches closest to expiry are prioritised at checkout.",
            tags = listOf(
                Tag("Best fit", Color(0xFF1C4EA3), Color(0xFFE7F0FF)),
                Tag("High priority", Color(0xFF92301B), Color(0xFFF9E5DC)),
                Tag("Low effort", Color(0xFF000000), Color(0x0D000000))
            ),
            sales = "18 450₼",
            waste = "-0.45%"
        ),
        RecommendationItem(
            id = "2",
            type = RecommendationType.BUNDLE,
            title = "Combo bundle",
            description = "Bundle Lays and Cheetos for just 3.99₼ to boost cross sales.",
            tags = listOf(
                Tag("Low effort", Color(0xFF000000), Color(0x0D000000)),
                Tag("Medium priority", Color(0xFF000000), Color(0x0D000000))
            ),
            sales = "4 450₼",
            waste = "-0.11%"
        ),
        RecommendationItem(
            id = "3",
            type = RecommendationType.TRANSFER,
            title = "Stock transfer from Xatai to 28 Mall",
            description = "Move 30% of Xatai overstock to 28 Mall, where velocity is 2.3× higher and shelf space is available.",
            tags = listOf(
                Tag("Medium effort", Color(0xFF000000), Color(0x0D000000)),
                Tag("Medium priority", Color(0xFF000000), Color(0x0D000000))
            ),
            sales = "4 450₼",
            waste = "-0.11%"
        )
    ),
    val selectedRecommendation: RecommendationItem? = null
)

enum class RecommendationType { DISCOUNT, BUNDLE, TRANSFER }
data class Tag(val label: String, val color: Color, val backgroundColor: Color)
data class RecommendationItem(
    val id: String,
    val type: RecommendationType,
    val title: String,
    val description: String,
    val tags: List<Tag>,
    val sales: String,
    val waste: String
)

class RecommendationsViewModel(
    private val navigator: Navigator,
    private val errorReporter: ErrorReporter,
    private val userRepository: UserRepository
) : ViewModel(), Stateful<RecommendationState> by statefulViewModel(RecommendationState()), KoinComponent {

    private val dashboardScreen: DashboardScreen by inject()
    private val forecastScreen: ForecastScreen by inject()

    init {
        observeUser()
    }

    private fun observeUser() {
        viewModelScope.launch {
            userRepository.userName.collect { name ->
                updateState { it.copy(userName = name) }
            }
        }
    }

    fun onSidebarItemClick(index: Int) {
        when (index) {
            0 -> navigator.navigateTo(dashboardScreen)
            1 -> navigator.navigateTo(forecastScreen)
            2 -> {} // Already here
            else -> {
                errorReporter.reportError("Feature not implemented yet")
            }
        }
    }

    fun logout() {
        navigator.back()
    }

    fun onApply(id: String) {
        val recommendation = state.value.recommendations.find { it.id == id }
        updateState { it.copy(selectedRecommendation = recommendation) }
    }

    fun onConfirmApply() {
        // Handle confirmed apply
        updateState { it.copy(selectedRecommendation = null) }
    }

    fun onDismissDialog() {
        updateState { it.copy(selectedRecommendation = null) }
    }
}

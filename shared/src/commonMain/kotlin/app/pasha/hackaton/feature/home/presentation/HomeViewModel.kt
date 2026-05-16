package app.pasha.hackaton.feature.home.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.feature.forecast.presentation.ForecastScreen
import app.pasha.hackaton.feature.recommendations.presentation.RecommendationsScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class HomeState(
    val userName: String = "Ravan A.",
    val sidebarItems: List<String> = listOf("Dashboard", "Forecast", "Recommendations", "Applied actions", "Branch analysis"),
    val selectedSidebarIndex: Int = 0,
    val opportunity: String = "48 250,",
    val opportunityFraction: String = "22 ₼",
    val opportunityGrowth: String = "+14% vs May",
    val atRiskTotal: Int = 12,
    val atRiskTracked: Int = 84,
    val atRiskItems: List<AtRiskItem> = listOf(
        AtRiskItem("Critical", 4, Color(0xFF92301B), Color(0xFFF9E5DC)),
        AtRiskItem("High", 5, Color(0xFF7B550B), Color(0xFFFBECC8)),
        AtRiskItem("Medium", 3, Color(0xFF1C4EA3), Color(0xFFE7F0FF))
    ),
    val predictedWaste: String = "3.2%",
    val actionCards: List<ActionCardItem> = listOf(
        ActionCardItem(4, "24 750₼", "Critical waste + high sales opp.", "Push discounts", Color(0xFF92301B), Color(0xFFF9E5DC)),
        ActionCardItem(5, "14 300₼", "Medium-risk overstock", "Push discounts", Color(0xFF7B550B), Color(0xFFFBECC8)),
        ActionCardItem(3, "6 400₼", "Slow movers", "Push discounts", Color(0xFF1C4EA3), Color(0xFFE7F0FF))
    ),
    val forecastRows: List<ForecastRowItem> = listOf(
        ForecastRowItem("Sumgayit", "Greens", "5.68%", "Critical", "+ 18 400₼", "Critical risk", isCritical = true),
        ForecastRowItem("Xatai", "Bakery", "4.12%", "High", "+ 12 300₼", "High risk"),
        ForecastRowItem("Bakikhanov", "Pastry", "3.44%", "Medium", "+ 6 400₼", "Medium risk"),
        ForecastRowItem("Bakikhanov", "Pastry", "3.44%", "Medium", "+ 6 400₼", "Medium risk")
    )
)

data class AtRiskItem(val label: String, val count: Int, val color: Color, val backgroundColor: Color)
data class ActionCardItem(val items: Int, val amount: String, val title: String, val tag: String, val color: Color, val backgroundColor: Color)
data class ForecastRowItem(val branch: String, val category: String, val wasteRisk: String, val level: String, val salesOpp: String, val aiCluster: String, val isCritical: Boolean = false)

class HomeViewModel(
    private val navigator: Navigator,
) : ViewModel(), Stateful<HomeState> by statefulViewModel(HomeState()), KoinComponent {

    private val forecastScreen: ForecastScreen by inject()
    private val recommendationsScreen: RecommendationsScreen by inject()

    fun logout() {
        navigator.back()
    }
}

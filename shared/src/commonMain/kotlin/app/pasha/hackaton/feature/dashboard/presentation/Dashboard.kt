package app.pasha.hackaton.feature.dashboard.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface DashboardState {
    data object Loading : DashboardState

    data class Ready(
        val userName: String = "",
        val totalIncome: String = "348 250,22 ₼",
        val totalWaste: String = "48 250,22 ₼",
        val potentialSave: String = "23 211,47₼",
        val opportunity: String = "48 250,",
        val opportunityFraction: String = "22 ₼",
        val opportunityGrowth: String = "+14% vs May",
        val atRiskItem: AtRiskItem = AtRiskItem(
            criticalCount = 0,
            highCount = 0,
            mediumCount = 0,
            totalCount = 0,
            trackedCount = 0,
        ),
        val predictedWaste: String = "3.2%",
        val actionCards: List<ActionCardItem> = emptyList(),
        val forecastRows: List<ForecastRowItem> = emptyList(),
        val quickActions: List<QuickActionItem> = emptyList(),
        val lastOperations: List<OperationItem> = emptyList(),
        val sidebarItems: List<String> = listOf("Main", "Operations", "Venues", "Warehouse", "Order history"),
        val selectedSidebarIndex: Int = 0,
    ) : DashboardState
}

data class AtRiskItem(
    val criticalCount: Int,
    val highCount: Int,
    val mediumCount: Int,
    val totalCount: Int,
    val trackedCount: Int,
)

data class ActionCardItem(
    val items: Int,
    val amount: String,
    val title: String,
    val tag: String,
    val color: Color,
    val backgroundColor: Color,
)

data class ForecastRowItem(
    val branch: String,
    val category: String,
    val wasteRisk: String,
    val level: String,
    val salesOpp: String,
    val aiCluster: String,
    val isCritical: Boolean = false,
)

data class QuickActionItem(
    val icon: ImageVector,
    val title: String,
    val badgeCount: Int? = null,
    val subtitle: String? = null
)

data class OperationItem(
    val icon: ImageVector,
    val name: String,
    val category: String,
    val date: String,
    val amount: String,
    val extraInfo: String? = null,
    val isNegative: Boolean = false
)

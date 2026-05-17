package app.pasha.hackaton.feature.operations.presentation

import androidx.compose.ui.graphics.vector.ImageVector

sealed interface OperationsState {
    data object Loading : OperationsState

    data class Ready(
        val userName: String = "",
        val operations: List<OperationItem> = emptyList(),
        val selectedTab: OperationTab = OperationTab.All
    ) : OperationsState
}

enum class OperationTab(val title: String) {
    All("All operations"),
    Transfers("Transfers"),
    Optimisation("Optimisation"),
    Discounts("Discounts"),
    More("More")
}

data class OperationItem(
    val icon: ImageVector,
    val title: String,
    val time: String,
    val description: String,
    val rightTitle: String,
    val rightSubtitle: String,
    val statusColor: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color(0xFF20B649),
    val amount: String? = null
)

package app.pasha.hackaton.feature.forecast.presentation

import androidx.compose.ui.graphics.Color

data class ForecastState(
    val userName: String = "",
    val sidebarItems: List<String> = listOf("Dashboard", "Forecast", "Recommendations", "Applied actions", "Branch analysis"),
    val selectedSidebarIndex: Int = 1,
    val venues: List<String> = listOf("Sumgayit", "Xatai", "Bakikhanov"),
    val selectedVenue: String = "Sumgayit",
    val cards: List<ForecastCardItem> = listOf(
        ForecastCardItem("Predicted waste", "5.68%", "+1.4% vs May", Color(0xFF92301B)),
        ForecastCardItem("Sales opp", "18 450₼", "Recoverable", Color.Black),
        ForecastCardItem("Stock pressure", "67%", "out of 100", Color.Black),
        ForecastCardItem("Days to expiry", "6.2", "Average batch", Color.Black)
    )
)

data class ForecastCardItem(
    val title: String,
    val value: String,
    val subtitle: String,
    val valueColor: Color
)

sealed interface ForecastIntent {
    data class SelectSidebarItem(val index: Int) : ForecastIntent
    data class SelectVenue(val venue: String) : ForecastIntent
    data object Logout : ForecastIntent
}

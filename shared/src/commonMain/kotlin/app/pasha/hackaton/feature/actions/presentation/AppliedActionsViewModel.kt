package app.pasha.hackaton.feature.actions.presentation

import androidx.lifecycle.ViewModel
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import org.koin.core.component.KoinComponent

data class AppliedActionsState(
    val userName: String = "Ravan A.",
    val summaryCards: List<SummaryCardItem> = listOf(
        SummaryCardItem("Active", "8"),
        SummaryCardItem("Applied - Last 14 days", "14"),
        SummaryCardItem("Estimated lift", "32 450₼"),
        SummaryCardItem("Waste change", "-1.2%")
    ),
    val actionRows: List<ActionRowItem> = listOf(
        ActionRowItem("Sumgayit", "Vegetables", "Discount", "Ravan Alasgarov", "13 May 2026", "Active"),
        ActionRowItem("Ganja", "Fruits", "No Discount", "Aysel Mammadova", "15 May 2026", "Active"),
        ActionRowItem("Lankaran", "Dairy", "20% Off", "Orkhan Hajiyev", "16 May 2026", "Inactive"),
        ActionRowItem("Baku", "Grains", "Buy 1 Get 1", "Nigar Akperova", "17 May 2026", "Active"),
        ActionRowItem("Mingachevir", "Meat", "10% Off", "Ilham Rustamov", "18 May 2026", "Active"),
        ActionRowItem("Sheki", "Fish", "No Discount", "Gulnara Veliyeva", "19 May 2026", "Inactive"),
        ActionRowItem("Goychay", "Pulses", "15% Off", "Kamran Jafarov", "20 May 2026", "Active"),
        ActionRowItem("Shamkir", "Spices", "10% Off", "Fidan Azizova", "21 May 2026", "Inactive"),
        ActionRowItem("Zagatala", "Nuts", "5% Off", "Rashad Mammadov", "22 May 2026", "Active"),
        ActionRowItem("Seki", "Beverages", "No Discount", "Sahil Bayramov", "23 May 2026", "Active"),
        ActionRowItem("Beylagan", "Bakery", "Special Offer", "Leyla Asadova", "24 May 2026", "Active"),
        ActionRowItem("Goygol", "Sweets", "30% Off", "Eldar Quliyev", "25 May 2026", "Inactive"),
        ActionRowItem("Khalaj", "Frozen Foods", "20% Off", "Naila Rahimova", "26 May 2026", "Active")
    )
)

data class SummaryCardItem(val title: String, val value: String)
data class ActionRowItem(val branch: String, val category: String, val action: String, val appliedBy: String, val date: String, val status: String)

class AppliedActionsViewModel(
    private val navigator: Navigator,
) : ViewModel(), Stateful<AppliedActionsState> by statefulViewModel(AppliedActionsState()), KoinComponent {

}

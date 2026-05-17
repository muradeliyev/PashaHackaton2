package app.pasha.hackaton.feature.dashboard.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Tag
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.network.api.PashaApi
import app.pasha.hackaton.core.network.model.PredictionRequest
import app.pasha.hackaton.core.storage.UserRepository
import app.pasha.hackaton.feature.operations.presentation.OperationsScreen
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DashboardViewModel(
    private val navigator: app.pasha.hackaton.core.navigation.Navigator,
    private val pashaApi: PashaApi,
    private val userRepository: UserRepository,
) : ViewModel(), Stateful<DashboardState> by statefulViewModel(DashboardState.Loading), KoinComponent {

    private val operationsScreen: OperationsScreen by inject()

    init {
        observeUser()
        loadData()
    }

    private fun observeUser() {
        viewModelScope.launch {
            userRepository.userName.collect { name ->
                updateState { state ->
                    when (state) {
                        DashboardState.Loading -> DashboardState.Loading
                        is DashboardState.Ready -> state.copy(userName = name)
                    }
                }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val userInfoResult = pashaApi.getUserInfo()
            val userName = userInfoResult.getOrNull()?.username.orEmpty()
            userInfoResult.onSuccess { info ->
                userRepository.updateUserName(info.username)
            }

            val branches = listOf("Sumgayit", "Khatai", "Bakikhanov")
            val predictions = branches.map { branch ->
                async {
                    pashaApi.predict(
                        PredictionRequest(
                            branch = branch,
                            family = "Greens",
                            month = 6,
                            quarter = 2,
                            timeIndex = 1,
                            receptionCount = 100,
                            lag1 = 0.0,
                            lag2 = 0.0,
                            rolling3Mean = 0.0,
                        )
                    )
                }
            }.awaitAll()

            val rows = predictions.mapNotNull { result ->
                result.getOrNull()?.let { prediction ->
                    ForecastRowItem(
                        branch = prediction.branch,
                        category = prediction.family,
                        wasteRisk = "${prediction.predictedWastePercent.toInt()}%",
                        level = prediction.riskLevel,
                        salesOpp = "${prediction.estimatedSalesIncreaseAzn.toInt()}₼",
                        aiCluster = prediction.cluster,
                        isCritical = prediction.riskLevel == "Critical"
                    )
                }
            }

            val atRiskItem = AtRiskItem(
                criticalCount = rows.count { it.level == "Critical" },
                highCount = rows.count { it.level == "High" },
                mediumCount = rows.count { it.level == "Medium" },
                totalCount = rows.size,
                trackedCount = rows.count { it.level != "Low" },
            )

            val quickActions = listOf(
                QuickActionItem(Icons.Default.Assessment, "Analyze stock"),
                QuickActionItem(
                    Icons.Default.LocalShipping,
                    "Order items",
                    badgeCount = 12,
                    subtitle = "2 pending"
                ),
                QuickActionItem(Icons.Default.Payments, "Waste reduction", badgeCount = 5),
                QuickActionItem(Icons.Default.Storefront, "Store audit", badgeCount = 2),
                QuickActionItem(Icons.Default.ConfirmationNumber, "Promotions")
            )

            val lastOperations = listOf(
                OperationItem(
                    Icons.Default.Tag,
                    "Discount applied",
                    "Greens",
                    "Today, 14:20",
                    "250,00 ₼",
                    extraInfo = "Sumgayit",
                    isNegative = true
                ),
                OperationItem(
                    Icons.Default.LocalShipping,
                    "Restock",
                    "Dairy",
                    "Today, 12:00",
                    "1 200,00 ₼",
                    extraInfo = "Khatai"
                ),
                OperationItem(
                    Icons.Default.Storefront,
                    "Inventory check",
                    "Bakery",
                    "Yesterday",
                    "45 items",
                    extraInfo = "Bakikhanov"
                ),
                OperationItem(
                    Icons.Default.LocalShipping,
                    "Shipment received",
                    "Fruits",
                    "Yesterday",
                    "800,00 ₼",
                    extraInfo = "Khatai"
                )
            )

            updateState {
                DashboardState.Ready(
                    userName = userName,
                    forecastRows = rows,
                    atRiskItem = atRiskItem,
                    quickActions = quickActions,
                    lastOperations = lastOperations
                )
            }
        }
    }
}

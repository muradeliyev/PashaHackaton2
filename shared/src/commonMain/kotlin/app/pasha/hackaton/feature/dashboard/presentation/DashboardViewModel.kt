package app.pasha.hackaton.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.network.api.PashaApi
import app.pasha.hackaton.core.network.model.PredictionRequest
import app.pasha.hackaton.core.storage.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DashboardViewModel(
    private val navigator: Navigator,
    private val pashaApi: PashaApi,
    private val userRepository: UserRepository,
) : ViewModel(), Stateful<DashboardState> by statefulViewModel(DashboardState.Loading), KoinComponent {

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
                            rolling3Mean = 0.0
                        )
                    )
                }
            }.awaitAll()

            val rows = predictions.mapIndexedNotNull { index, result ->
                result.getOrNull()?.let { prediction ->
                    ForecastRowItem(
                        branch = prediction.branch,
                        category = prediction.family,
                        wasteRisk = "${prediction.predictedWastePercent}%",
                        level = prediction.riskLevel,
                        salesOpp = "${prediction.estimatedSalesIncreaseAzn}₼",
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

            updateState {
                DashboardState.Ready(
                    userName = userName,
                    forecastRows = rows,
                    atRiskItem = atRiskItem,
                )
            }
        }
    }
}

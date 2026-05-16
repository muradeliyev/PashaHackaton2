package app.pasha.hackaton.feature.forecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.storage.UserRepository
import app.pasha.hackaton.feature.dashboard.presentation.DashboardScreen
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ForecastViewModel(
    private val navigator: Navigator,
    private val userRepository: UserRepository,
) : ViewModel(), Stateful<ForecastState> by statefulViewModel(ForecastState.Loading), KoinComponent {

    private val dashboardScreen: DashboardScreen by inject()

    init {
        observeUser()
        loadData()
    }

    private fun observeUser() {
        viewModelScope.launch {
            userRepository.userName.collect { name ->
                updateState { state ->
                    when (state) {
                        ForecastState.Loading -> ForecastState.Loading
                        is ForecastState.Ready -> state.copy(userName = name)
                    }
                }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            updateState { ForecastState.Ready() }
        }
    }

    fun onIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.SelectSidebarItem -> {
                if (intent.index == 0) {
                    navigator.navigateTo(dashboardScreen)
                } else {
                    updateState { state ->
                        when (state) {
                            ForecastState.Loading -> state
                            is ForecastState.Ready -> state.copy(selectedSidebarIndex = intent.index)
                        }
                    }
                }
            }
            is ForecastIntent.SelectVenue -> {
                updateState { state ->
                    when (state) {
                        ForecastState.Loading -> state
                        is ForecastState.Ready -> state.copy(selectedVenue = intent.venue)
                    }
                }
            }
            ForecastIntent.Logout -> {
                navigator.back()
            }
        }
    }
}

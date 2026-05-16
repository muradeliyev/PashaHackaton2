package app.pasha.hackaton.feature.forecast.presentation

import androidx.lifecycle.ViewModel
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.feature.home.presentation.HomeScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ForecastViewModel(
    private val navigator: Navigator,
) : ViewModel(), Stateful<ForecastState> by statefulViewModel(ForecastState()), KoinComponent {

    private val homeScreen: HomeScreen by inject()

    fun onIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.SelectSidebarItem -> {
                if (intent.index == 0) {
                    navigator.navigateTo(homeScreen)
                } else {
                    updateState { it.copy(selectedSidebarIndex = intent.index) }
                }
            }
            is ForecastIntent.SelectVenue -> {
                updateState { it.copy(selectedVenue = intent.venue) }
            }
            ForecastIntent.Logout -> {
                navigator.back()
            }
        }
    }
}

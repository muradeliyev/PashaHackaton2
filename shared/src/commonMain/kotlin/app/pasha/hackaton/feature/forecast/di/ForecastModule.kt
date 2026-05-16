package app.pasha.hackaton.feature.forecast.di

import app.pasha.hackaton.feature.forecast.presentation.ForecastScreen
import app.pasha.hackaton.feature.forecast.presentation.ForecastViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.plugin.module.dsl.viewModel

val forecastModule = module {
    viewModel<ForecastViewModel>()

    navigation<ForecastScreen> {
        it.Content()
    }
}

package app.pasha.hackaton.feature.forecast.di

import app.pasha.hackaton.feature.forecast.presentation.ForecastScreen
import app.pasha.hackaton.feature.forecast.presentation.ForecastViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.core.module.dsl.viewModel

val forecastModule = module {
    viewModel { ForecastViewModel(get(), get()) }

    navigation<ForecastScreen> {
        it.Content()
    }
}

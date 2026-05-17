package app.pasha.hackaton.feature.dashboard.di

import app.pasha.hackaton.feature.dashboard.presentation.DashboardScreen
import app.pasha.hackaton.feature.dashboard.presentation.DashboardViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.core.module.dsl.viewModel

val dashboardModule = module {
    viewModel { DashboardViewModel(get(), get(), get()) }

    navigation<DashboardScreen> {
        it.Content()
    }
}

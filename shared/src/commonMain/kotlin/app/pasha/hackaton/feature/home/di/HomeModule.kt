package app.pasha.hackaton.feature.home.di

import app.pasha.hackaton.feature.home.presentation.HomeScreen
import app.pasha.hackaton.feature.home.presentation.HomeViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.core.module.dsl.viewModel

val homeModule = module {
    viewModel { HomeViewModel(get()) }

    navigation<HomeScreen> {
        it.Content()
    }
}

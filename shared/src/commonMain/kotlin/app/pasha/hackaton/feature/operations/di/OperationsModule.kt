package app.pasha.hackaton.feature.operations.di

import app.pasha.hackaton.feature.operations.presentation.OperationsScreen
import app.pasha.hackaton.feature.operations.presentation.OperationsViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.core.module.dsl.viewModel

val operationsModule = module {
    viewModel { OperationsViewModel(get(), get()) }
    single { OperationsScreen(get()) }

    navigation<OperationsScreen> {
        it.Content()
    }
}

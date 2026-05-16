package app.pasha.hackaton.feature.actions.di

import app.pasha.hackaton.feature.actions.presentation.AppliedActionsScreen
import app.pasha.hackaton.feature.actions.presentation.AppliedActionsViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.core.module.dsl.viewModel

val appliedActionsModule = module {
    viewModel { AppliedActionsViewModel(get(), get(), get(), get()) }
    single { AppliedActionsScreen() }

    navigation<AppliedActionsScreen> {
        it.Content()
    }
}

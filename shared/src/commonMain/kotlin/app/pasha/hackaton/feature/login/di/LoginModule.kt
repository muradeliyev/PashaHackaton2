package app.pasha.hackaton.feature.login.di

import app.pasha.hackaton.feature.login.presentation.LoginScreen
import app.pasha.hackaton.feature.login.presentation.LoginViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.plugin.module.dsl.viewModel


internal val loginModule = module {
    viewModel<LoginViewModel>()

    navigation<LoginScreen> {
        it.Content()
    }
}

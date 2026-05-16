package app.pasha.hackaton.feature.login.di

import app.pasha.hackaton.feature.login.presentation.LoginScreen
import app.pasha.hackaton.feature.login.presentation.LoginViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

val loginModule = module {
    factory { LoginViewModel(get(), get(), get()) }

    navigation<LoginScreen> {
        it.Content()
    }
}

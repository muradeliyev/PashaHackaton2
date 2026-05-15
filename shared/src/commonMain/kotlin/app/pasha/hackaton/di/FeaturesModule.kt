package app.pasha.hackaton.di

import app.pasha.hackaton.feature.home.di.homeModule
import app.pasha.hackaton.feature.login.di.loginModule
import org.koin.dsl.module


val featuresModule = module {
    includes(homeModule)
    includes(loginModule)
}

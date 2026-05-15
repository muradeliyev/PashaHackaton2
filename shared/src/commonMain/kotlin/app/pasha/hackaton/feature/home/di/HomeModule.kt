package app.pasha.hackaton.feature.home.di

import app.pasha.hackaton.feature.home.presentation.HomeViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory


val homeModule = module {
    factory<HomeViewModel>()
}

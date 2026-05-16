package app.pasha.hackaton.feature.recommendations.di

import app.pasha.hackaton.feature.recommendations.presentation.RecommendationsScreen
import app.pasha.hackaton.feature.recommendations.presentation.RecommendationsViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.core.module.dsl.viewModel

val recommendationsModule = module {
    viewModel { RecommendationsViewModel(get(), get()) }
    single { RecommendationsScreen() }

    navigation<RecommendationsScreen> {
        it.Content()
    }
}

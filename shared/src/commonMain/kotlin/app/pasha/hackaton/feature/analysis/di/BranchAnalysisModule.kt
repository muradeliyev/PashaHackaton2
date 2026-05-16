package app.pasha.hackaton.feature.analysis.di

import app.pasha.hackaton.feature.analysis.presentation.BranchAnalysisScreen
import app.pasha.hackaton.feature.analysis.presentation.BranchAnalysisViewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.koin.core.module.dsl.viewModel

val branchAnalysisModule = module {
    viewModel { BranchAnalysisViewModel(get()) }
    single { BranchAnalysisScreen() }

    navigation<BranchAnalysisScreen> {
        it.Content()
    }
}

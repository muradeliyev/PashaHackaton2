package app.pasha.hackaton.di

import app.pasha.hackaton.feature.actions.di.appliedActionsModule
import app.pasha.hackaton.feature.analysis.di.branchAnalysisModule
import app.pasha.hackaton.feature.home.di.homeModule
import app.pasha.hackaton.feature.login.di.loginModule
import app.pasha.hackaton.feature.recommendations.di.recommendationsModule
import org.koin.dsl.module


val featuresModule = module {
    includes(homeModule)
    includes(loginModule)
    includes(recommendationsModule)
    includes(appliedActionsModule)
    includes(branchAnalysisModule)
}

package app.pasha.hackaton.di

import app.pasha.hackaton.core.di.coreDiModule
import app.pasha.hackaton.core.di.platformCoreModule
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.navigation.impl.NavigatorImpl
import app.pasha.hackaton.core.storage.UserRepository
import app.pasha.hackaton.feature.forecast.di.forecastModule
import app.pasha.hackaton.feature.forecast.presentation.ForecastScreen
import app.pasha.hackaton.feature.dashboard.di.dashboardModule
import app.pasha.hackaton.feature.dashboard.presentation.DashboardScreen
import app.pasha.hackaton.feature.login.di.loginModule
import app.pasha.hackaton.feature.login.presentation.LoginScreen
import app.pasha.hackaton.feature.recommendations.di.recommendationsModule
import app.pasha.hackaton.feature.recommendations.presentation.RecommendationsScreen
import app.pasha.hackaton.feature.actions.di.appliedActionsModule
import app.pasha.hackaton.feature.actions.presentation.AppliedActionsScreen
import app.pasha.hackaton.feature.analysis.di.branchAnalysisModule
import app.pasha.hackaton.feature.analysis.presentation.BranchAnalysisScreen
import app.pasha.hackaton.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.dsl.bind


val coreModule = module {
    single { NavigatorImpl() }.bind(Navigator::class)
    single { UserRepository() }
    factory { LoginScreen(get()) }
    factory { DashboardScreen(get()) }
    factory { ForecastScreen(get()) }
    factory { RecommendationsScreen() }
    factory { AppliedActionsScreen() }
    factory { BranchAnalysisScreen() }
}

fun setupKoin() {
    startKoin {
        printLogger(Level.ERROR)

        modules(
            coreDiModule,
            platformCoreModule(),
            domainModule,
            coreModule,
            dashboardModule,
            loginModule,
            forecastModule,
            recommendationsModule,
            appliedActionsModule,
            branchAnalysisModule,
        )
    }
}

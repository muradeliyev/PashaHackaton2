package app.pasha.hackaton.di

import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.navigation.impl.NavigatorImpl
import app.pasha.hackaton.feature.home.di.homeModule
import app.pasha.hackaton.feature.login.di.loginModule
import app.pasha.hackaton.feature.login.presentation.LoginScreen
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.plugin.module.dsl.bind
import org.koin.plugin.module.dsl.factory
import org.koin.plugin.module.dsl.single


val coreModule = module {
    single<NavigatorImpl>().bind(Navigator::class)
    factory<LoginScreen>()
}

fun setupKoin() {
    startKoin {
        printLogger(Level.ERROR)

        modules(
            coreModule,
            homeModule,
            loginModule,
        )
    }
}

package app.pasha.hackaton.di

import app.pasha.hackaton.core.navigation.di.navigationModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


fun setupKoin() {
    startKoin {
        printLogger(Level.ERROR)

        modules(
            navigationModule,
            featuresModule,
        )
    }
}

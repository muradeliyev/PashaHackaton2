package app.pasha.hackaton.core.navigation.di

import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.navigation.impl.NavigatorImpl
import org.koin.dsl.module
import org.koin.plugin.module.dsl.bind
import org.koin.plugin.module.dsl.single


val navigationModule = module(createdAtStart = true) {
    single<Navigator>().bind(NavigatorImpl::class)
}

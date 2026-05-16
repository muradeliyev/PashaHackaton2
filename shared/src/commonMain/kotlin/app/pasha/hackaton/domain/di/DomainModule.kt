package app.pasha.hackaton.domain.di

import app.pasha.hackaton.domain.auth.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { LoginUseCase(get(), get()) }
}

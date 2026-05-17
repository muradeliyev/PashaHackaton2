package app.pasha.hackaton.core.di

import app.pasha.hackaton.core.error.ErrorReporter
import app.pasha.hackaton.core.error.ErrorReporterImpl
import app.pasha.hackaton.core.network.api.PashaApi
import app.pasha.hackaton.core.network.impl.PashaApiImpl
import app.pasha.hackaton.core.storage.AppStorage
import app.pasha.hackaton.core.storage.AppStorageImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


private const val BASE_URL = "https://cordless-promises-drawing-moss.trycloudflare.com/"


val coreDiModule = module {
    single<HttpClient> {
        val appStorage = get<AppStorage>()
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = appStorage.getAccessToken().first()
                        if (token != null) {
                            BearerTokens(token, "")
                        } else {
                            null
                        }
                    }
                }
            }
            defaultRequest {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }
    }

    singleOf(::PashaApiImpl) { bind<PashaApi>() }
    singleOf(::AppStorageImpl) { bind<AppStorage>() }
    singleOf(::ErrorReporterImpl) { bind<ErrorReporter>() }
}

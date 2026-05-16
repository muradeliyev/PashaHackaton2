package app.pasha.hackaton.core.network.impl

import app.pasha.hackaton.core.network.api.PashaApi
import app.pasha.hackaton.core.network.model.ForecastRequest
import app.pasha.hackaton.core.network.model.ForecastResponse
import app.pasha.hackaton.core.network.model.LoginRequest
import app.pasha.hackaton.core.network.model.LoginResponse
import app.pasha.hackaton.core.network.model.UserInfoResponse
import app.pasha.hackaton.core.network.model.PredictionRequest
import app.pasha.hackaton.core.network.model.PredictionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PashaApiImpl(
    private val httpClient: HttpClient
) : PashaApi {

    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return runCatching {
            httpClient.post("v1/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }
    }

    override suspend fun getUserInfo(): Result<UserInfoResponse> {
        return runCatching {
            httpClient.get("v1/users/user-info").body()
        }
    }

    override suspend fun predict(request: PredictionRequest): Result<PredictionResponse> {
        return runCatching {
            httpClient.post("v1/forecast/predict") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }
    }

    override suspend fun forecast(request: ForecastRequest): Result<ForecastResponse> {
        return runCatching {
            httpClient.post("v1/forecast") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }
    }
}

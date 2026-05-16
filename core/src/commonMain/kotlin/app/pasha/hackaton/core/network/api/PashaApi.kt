package app.pasha.hackaton.core.network.api

import app.pasha.hackaton.core.network.model.ForecastRequest
import app.pasha.hackaton.core.network.model.ForecastResponse
import app.pasha.hackaton.core.network.model.LoginRequest
import app.pasha.hackaton.core.network.model.LoginResponse
import app.pasha.hackaton.core.network.model.UserInfoResponse
import app.pasha.hackaton.core.network.model.PredictionRequest
import app.pasha.hackaton.core.network.model.PredictionResponse

interface PashaApi {
    suspend fun login(request: LoginRequest): Result<LoginResponse>
    suspend fun getUserInfo(): Result<UserInfoResponse>
    suspend fun predict(request: PredictionRequest): Result<PredictionResponse>
    suspend fun forecast(request: ForecastRequest): Result<ForecastResponse>
}

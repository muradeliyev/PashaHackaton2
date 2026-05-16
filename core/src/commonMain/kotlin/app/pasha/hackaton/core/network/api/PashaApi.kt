package app.pasha.hackaton.core.network.api

import app.pasha.hackaton.core.network.model.LoginRequest
import app.pasha.hackaton.core.network.model.LoginResponse

interface PashaApi {
    suspend fun login(request: LoginRequest): Result<LoginResponse>
}

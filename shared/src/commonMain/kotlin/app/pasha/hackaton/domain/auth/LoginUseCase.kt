package app.pasha.hackaton.domain.auth

import app.pasha.hackaton.core.network.api.PashaApi
import app.pasha.hackaton.core.network.model.LoginRequest
import app.pasha.hackaton.core.storage.AppStorage

class LoginUseCase(
    private val pashaApi: PashaApi,
    private val appStorage: AppStorage
) {
    suspend operator fun invoke(request: LoginRequest): Result<Unit> {
        return pashaApi.login(request).mapCatching { response ->
            appStorage.saveAccessToken(response.accessToken)
        }
    }
}

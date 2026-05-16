package app.pasha.hackaton.core.network.impl

import app.pasha.hackaton.core.network.api.PashaApi
import app.pasha.hackaton.core.network.model.LoginRequest
import app.pasha.hackaton.core.network.model.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
}

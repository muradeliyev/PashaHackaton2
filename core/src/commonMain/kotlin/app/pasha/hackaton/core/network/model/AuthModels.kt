package app.pasha.hackaton.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val accessToken: String,
    val expiresIn: Int
)

@Serializable
data class UserInfoResponse(
    val id: Int,
    val username: String
)

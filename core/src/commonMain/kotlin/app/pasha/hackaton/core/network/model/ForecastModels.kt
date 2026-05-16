package app.pasha.hackaton.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ForecastRequest(
    val branch: String,
    val month: Int,
    val year: Int,
    val limit: Int,
)


@Serializable
data class ForecastResponse(
    val month: Int,
    val year: Int,
    val branch: String,
    val item: List<ForecastItemDto>,
)


@Serializable
data class ForecastItemDto(
    val branch: String,
    val family: String,
    val month: Int,
    val year: Int,
    val cluster: String,
    val recommendations: List<String>,
    @SerialName("predicted_waste_percent") val predictedWastePercent: Float,
    @SerialName("risk_score") val riskScore: Int,
    @SerialName("risk_level") val riskLevel: String,
)

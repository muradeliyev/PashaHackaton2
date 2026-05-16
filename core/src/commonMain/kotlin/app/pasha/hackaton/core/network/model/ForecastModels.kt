package app.pasha.hackaton.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PredictionRequest(
    val branch: String,
    val family: String,
    val month: Int,
    val quarter: Int,
    @SerialName("time_index") val timeIndex: Int,
    @SerialName("reception_count") val receptionCount: Int,
    @SerialName("lag_1") val lag1: Double,
    @SerialName("lag_2") val lag2: Double,
    @SerialName("rolling_3_mean") val rolling3Mean: Double
)

@Serializable
data class PredictionResponse(
    val branch: String,
    val family: String,
    val cluster: String,
    val confidence: Int,
    val recommendations: List<String>,
    @SerialName("predicted_waste_percent") val predictedWastePercent: Double,
    @SerialName("risk_score") val riskScore: Double,
    @SerialName("risk_level") val riskLevel: String,
    @SerialName("estimated_sales_increase_azn") val estimatedSalesIncreaseAzn: Double,
    @SerialName("estimated_waste_reduction_percent") val estimatedWasteReductionPercent: Double
)

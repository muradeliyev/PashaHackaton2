package app.pasha.hackaton.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActionHistoryRequest(
    val branch: String,
    val family: String,
)

@Serializable
data class ActionHistoryResponse(
    val items: List<ActionHistoryItem>
)
@Serializable
data class ActionHistoryItem(
    val branch: String,
    val family: String,
    @SerialName("action_type") val actionType: String,
    @SerialName("applied_by") val appliedBy: String,
    @SerialName("applied_at") val appliedAt: String,
    val note: String,
    val status: String,
)

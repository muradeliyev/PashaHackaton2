package app.pasha.hackaton.feature.actions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.error.ErrorReporter
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.network.api.PashaApi
import app.pasha.hackaton.core.network.model.ActionHistoryItem
import app.pasha.hackaton.core.network.model.ActionHistoryRequest
import app.pasha.hackaton.core.storage.UserRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

private const val DefaultBranch = "yasamal"

sealed interface AppliedActionsState {
    data object Loading : AppliedActionsState

    data class Ready(
        val userName: String = "",
        val branch: String = DefaultBranch,
        val summaryCards: List<SummaryCardItem> = emptyList(),
        val actionRows: List<ActionRowItem> = emptyList(),
    ) : AppliedActionsState
}

data class SummaryCardItem(val title: String, val value: String)
data class ActionRowItem(
    val branch: String,
    val family: String,
    val actionType: String,
    val appliedBy: String,
    val appliedAt: String,
    val note: String,
    val status: String,
)

class AppliedActionsViewModel(
    private val navigator: Navigator,
    private val userRepository: UserRepository,
    private val pashaApi: PashaApi,
    private val errorReporter: ErrorReporter,
) : ViewModel(), Stateful<AppliedActionsState> by statefulViewModel(AppliedActionsState.Loading), KoinComponent {

    init {
        loadData()
        observeUser()
    }

    private fun loadData() {
        viewModelScope.launch {
            val result = pashaApi.getActionHistory(ActionHistoryRequest(branch = DefaultBranch, ""))

            result.onSuccess { response ->
                val rows = response.items.map { it.toActionRowItem() }
                updateState {
                    AppliedActionsState.Ready(
                        userName = userRepository.userName.value,
                        branch = DefaultBranch,
                        summaryCards = rows.toSummaryCards(DefaultBranch),
                        actionRows = rows,
                    )
                }
            }.onFailure { throwable ->
                errorReporter.reportError(throwable.message ?: "Failed to load applied actions")
                updateState {
                    AppliedActionsState.Ready(
                        userName = userRepository.userName.value,
                        branch = DefaultBranch,
                        summaryCards = emptyList<ActionRowItem>().toSummaryCards(DefaultBranch),
                        actionRows = emptyList(),
                    )
                }
            }
        }
    }

    private fun observeUser() {
        viewModelScope.launch {
            userRepository.userName.collect { name ->
                updateState {
                    when (it) {
                        AppliedActionsState.Loading -> AppliedActionsState.Loading
                        is AppliedActionsState.Ready -> it.copy(userName = name)
                    }
                }
            }
        }
    }

    private fun ActionHistoryItem.toActionRowItem(): ActionRowItem {
        return ActionRowItem(
            branch = branch,
            family = family,
            actionType = actionType,
            appliedBy = appliedBy,
            appliedAt = appliedAt.toHumanReadableDateTime(),
            note = note,
            status = status,
        )
    }

    private fun List<ActionRowItem>.toSummaryCards(branch: String): List<SummaryCardItem> {
        return listOf(
            SummaryCardItem("Active", count { it.status.equals("Active", ignoreCase = true) }.toString()),
            SummaryCardItem("Applied actions", size.toString()),
            SummaryCardItem("Branch", branch),
            SummaryCardItem("Latest action", firstOrNull()?.appliedAt.orEmpty().ifBlank { "-" }),
        )
    }

}

private fun String.toHumanReadableDateTime(): String {
    val dateAndTime = trim()
    if (dateAndTime.isBlank()) return "-"

    val datePart = dateAndTime.substringBefore('T').takeIf { it != dateAndTime }
        ?: dateAndTime.substringBefore(' ')
    val dateSegments = datePart.split("-")
    if (dateSegments.size != 3) return dateAndTime

    val year = dateSegments[0].toIntOrNull() ?: return dateAndTime
    val month = dateSegments[1].toIntOrNull() ?: return dateAndTime
    val day = dateSegments[2].toIntOrNull() ?: return dateAndTime
    val monthName = monthNames.getOrNull(month - 1) ?: return dateAndTime

    val formattedDate = "$day $monthName $year"
    val timePart = dateAndTime
        .substringAfter('T', missingDelimiterValue = "")
        .ifBlank { dateAndTime.substringAfter(' ', missingDelimiterValue = "") }
        .substringBefore('.')
        .removeSuffix("Z")
        .take(5)

    return if (timePart.length == 5) {
        "$formattedDate, $timePart"
    } else {
        formattedDate
    }
}

private val monthNames = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec",
)

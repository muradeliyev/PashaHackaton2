package app.pasha.hackaton.feature.operations.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Tag
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pasha.hackaton.core.mvi.Stateful
import app.pasha.hackaton.core.mvi.statefulViewModel
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.storage.UserRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class OperationsViewModel(
    private val navigator: Navigator,
    private val userRepository: UserRepository
) : ViewModel(), Stateful<OperationsState> by statefulViewModel(OperationsState.Loading), KoinComponent {

    init {
        loadData()
        observeUser()
    }

    private fun loadData() {
        viewModelScope.launch {
            val mockOperations = listOf(
                OperationItem(
                    icon = Icons.Default.Tag,
                    title = "Bravo Sumqayıt ADY",
                    time = "09:44",
                    description = "Discounted items, Push notification",
                    rightTitle = "128 371 users",
                    rightSubtitle = "Done"
                ),
                OperationItem(
                    icon = Icons.Default.DeliveryDining,
                    title = "Goods transfer from Bravo Khatai to Bravo Yusif Safarov",
                    time = "09:21",
                    description = "Warehouse transfer",
                    rightTitle = "Transfer task",
                    rightSubtitle = "Done"
                ),
                OperationItem(
                    icon = Icons.Default.Storefront,
                    title = "Shelf optimisation",
                    time = "09:03",
                    description = "Əməkhaqqı köçürməsi",
                    rightTitle = "Optimisation task",
                    rightSubtitle = "In progress",
                    statusColor = Color(0xFFDD8B26)
                ),
                OperationItem(
                    icon = Icons.Default.LocalShipping,
                    title = "Order no: 9218736 (Redbull)",
                    time = "09:21",
                    description = "Order",
                    rightTitle = "30 ₼",
                    rightSubtitle = "Bank ləğv etdi",
                    statusColor = Color(0xFFDD3826)
                )
            )
            updateState { OperationsState.Ready(operations = mockOperations) }
        }
    }

    private fun observeUser() {
        viewModelScope.launch {
            userRepository.userName.collect { name ->
                updateState { state ->
                    when (state) {
                        OperationsState.Loading -> OperationsState.Loading
                        is OperationsState.Ready -> state.copy(userName = name)
                    }
                }
            }
        }
    }

    fun onTabSelected(tab: OperationTab) {
        updateState { state ->
            if (state is OperationsState.Ready) {
                state.copy(selectedTab = tab)
            } else state
        }
    }
}

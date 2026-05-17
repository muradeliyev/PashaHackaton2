package app.pasha.hackaton.feature.operations.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.pasha.hackaton.ui.kit.Typography
import app.pasha.hackaton.ui.kit.component.TopBar

@Composable
fun OperationsPage(viewModel: OperationsViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F6F6))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 40.dp)
    ) {
        when (val currentState = state) {
            OperationsState.Loading -> Unit // TODO
            is OperationsState.Ready -> OperationsContent(currentState, viewModel::onTabSelected)
        }
    }
}

@Composable
private fun OperationsContent(
    state: OperationsState.Ready,
    onTabSelected: (OperationTab) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(userName = state.userName)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OperationTab.entries.forEach { tab ->
                    val isSelected = state.selectedTab == tab
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(256.dp))
                            .background(if (isSelected) Color(0xFF8CC73E) else Color.White)
                            .clickable { onTabSelected(tab) }
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = tab.title,
                            style = Typography.l1,
                            color = if (isSelected) Color.White else Color.Black
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(256.dp))
                    .background(Color.White)
                    .clickable { }
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Filters", style = Typography.l1)
                    Icon(Icons.Default.FilterList, contentDescription = null, modifier = Modifier.size(20.dp))
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Box(Modifier.fillMaxWidth().height(1.dp).background(Color.Black.copy(alpha = 0.1f)))
        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Today", style = Typography.l2m, color = Color.Black.copy(alpha = 0.5f))

            state.operations.forEach { operation ->
                OperationRow(operation)
            }
        }
        
        Spacer(Modifier.height(48.dp))
    }
}

@Composable
private fun OperationRow(operation: OperationItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.size(44.dp).background(Color(0xFFF7F6F6), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(operation.icon, contentDescription = null, modifier = Modifier.size(24.dp))
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(operation.title, style = Typography.l1m)
            Text(
                "${operation.time} • ${operation.description}",
                style = Typography.l2m,
                color = Color.Black.copy(alpha = 0.5f)
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(operation.rightTitle, style = Typography.l1m)
            Text(
                operation.rightSubtitle,
                style = Typography.l2m,
                color = operation.statusColor
            )
        }

        Icon(
            Icons.Default.MoreVert,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black.copy(alpha = 0.3f)
        )
    }
}

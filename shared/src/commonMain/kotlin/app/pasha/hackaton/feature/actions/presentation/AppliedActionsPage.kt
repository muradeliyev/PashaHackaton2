package app.pasha.hackaton.feature.actions.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.pasha.hackaton.ui.kit.Typography
import app.pasha.hackaton.ui.kit.component.TopBar

@Composable
fun AppliedActionsPage(viewModel: AppliedActionsViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F6F6))
            .padding(horizontal = 56.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(userName = state.userName)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            state.summaryCards.forEach { card ->
                SummaryCard(title = card.title, value = card.value, modifier = Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(20.dp))

        ActionsTable(rows = state.actionRows)

        Spacer(Modifier.height(48.dp))
    }
}

@Composable
fun SummaryCard(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(184.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, style = Typography.l1m, color = Color.Black.copy(alpha = 0.5f))
        Text(value, style = Typography.h4)
    }
}

@Composable
fun ActionsTable(rows: List<ActionRowItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.08f), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val headers = listOf("Branch", "Category", "Action", "Applied by", "Date", "Status")
            headers.forEach { header ->
                Text(
                    text = header,
                    style = Typography.l2m,
                    color = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Rows
        rows.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(row.branch, style = Typography.l1, modifier = Modifier.weight(1f))
                Text(row.category, style = Typography.l1m, modifier = Modifier.weight(1f))
                Text(row.action, style = Typography.l1m, modifier = Modifier.weight(1f))
                Text(row.appliedBy, style = Typography.l1m, modifier = Modifier.weight(1f))
                Text(row.date, style = Typography.l1m, modifier = Modifier.weight(1f))
                Text(row.status, style = Typography.l1m, modifier = Modifier.weight(1f))
            }
        }
    }
}

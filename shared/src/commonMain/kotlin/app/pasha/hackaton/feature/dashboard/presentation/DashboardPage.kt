package app.pasha.hackaton.feature.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FilterList
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
import app.pasha.hackaton.ui.kit.component.SecondaryButton
import app.pasha.hackaton.ui.kit.component.ShimmerBox
import app.pasha.hackaton.ui.kit.component.StatusBadge
import app.pasha.hackaton.ui.kit.component.TopBar
import app.pasha.hackaton.ui.kit.component.TopBarShimmer

@Composable
fun DashboardPage(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F6F6))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 40.dp)
    ) {
        when (val currentState = state) {
            DashboardState.Loading -> DashboardLoading()
            is DashboardState.Ready -> DashboardContent(currentState)
        }
    }
}

@Composable
fun SidebarItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color(0xFFF7F6F6) else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = title,
            style = if (isSelected) Typography.l1m else Typography.l1,
            color = if (isSelected) Color.Black else Color.Black.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun DashboardContent(state: DashboardState.Ready) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(userName = state.userName)

        SummarySection(
            income = state.totalIncome,
            waste = state.totalWaste,
            potentialSave = state.potentialSave
        )

        Spacer(Modifier.height(40.dp))
        Box(Modifier.fillMaxWidth().height(1.dp).background(Color.Black.copy(alpha = 0.1f)))
        Spacer(Modifier.height(40.dp))

        QuickActionsSection(state.quickActions)

        Spacer(Modifier.height(40.dp))

        ForecastTable(rows = state.forecastRows)

        Spacer(Modifier.height(40.dp))

        LastOperationsSection(state.lastOperations)

        Spacer(Modifier.height(48.dp))
    }
}

@Composable
fun SummarySection(income: String, waste: String, potentialSave: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        Text("This month May, 2026", style = Typography.l1, color = Color.Black.copy(alpha = 0.5f))
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(80.dp)
        ) {
            Column {
                Text("Income", style = Typography.l2m, color = Color.Black.copy(alpha = 0.5f))
                Text(income, style = Typography.h0)
            }
            Column {
                Text("Waste", style = Typography.l2m, color = Color.Black.copy(alpha = 0.5f))
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(waste, style = Typography.h0)
                    Text(
                        "potential save can be $potentialSave",
                        style = Typography.l2m,
                        color = Color(0xFF3A5A28)
                    )
                }
            }
        }
    }
}

@Composable
fun QuickActionsSection(actions: List<QuickActionItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Quick actions", style = Typography.h4)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            actions.forEach { action ->
                QuickActionCard(action)
            }
        }
    }
}

@Composable
fun QuickActionCard(action: QuickActionItem) {
    Column(
        modifier = Modifier
            .size(140.dp, 120.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(action.icon, contentDescription = null, modifier = Modifier.size(24.dp))
            if (action.badgeCount != null) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFD42721), CircleShape)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(action.badgeCount.toString(), color = Color.White, style = Typography.c1)
                }
            }
        }
        Column {
            Text(action.title, style = Typography.l2m)
            if (action.subtitle != null) {
                Text(action.subtitle, style = Typography.c1, color = Color.Black.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
fun LastOperationsSection(operations: List<OperationItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Last operations", style = Typography.h4)
            SecondaryButton(onClick = {}) {
                Text("See all", style = Typography.l1m)
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
            operations.forEach { operation ->
                OperationRow(operation)
                Box(
                    Modifier.fillMaxWidth().height(1.dp).background(Color.Black.copy(alpha = 0.05f))
                )
            }
        }
    }
}

@Composable
fun OperationRow(operation: OperationItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier.size(44.dp).background(Color(0xFFF7F6F6), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(operation.icon, contentDescription = null, modifier = Modifier.size(24.dp))
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(operation.name, style = Typography.l1m)
            Text(
                "${operation.category} • ${operation.date}",
                style = Typography.l2m,
                color = Color.Black.copy(alpha = 0.5f)
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = (if (operation.isNegative) "-" else "+") + operation.amount,
                style = Typography.l1m,
                color = if (operation.isNegative) Color(0xFFD42721) else Color(0xFF3A5A28)
            )
            if (operation.extraInfo != null) {
                Text(
                    operation.extraInfo,
                    style = Typography.l2m,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }

        Icon(
            Icons.Default.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color.Black.copy(alpha = 0.3f)
        )
    }
}

@Composable
fun ForecastTable(rows: List<ForecastRowItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Forecast - June 2026", style = Typography.l1m)
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.1f), RoundedCornerShape(256.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("${rows.size} results", style = Typography.c1)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilterDropdown("All branches")
                FilterDropdown("All categories")
                FilterDropdown("High, Critical")
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(256.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Reset", style = Typography.l1m)
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.04f))
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            val columns =
                listOf("Branch", "Category", "Waste risk", "Level", "Sales opp", "AI cluster")
            columns.forEachIndexed { index, name ->
                Text(
                    text = name,
                    style = Typography.l2m,
                    color = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier.weight(if (index == 5) 0.8f else 1f)
                )
            }
        }

        rows.forEach { row ->
            ForecastRow(
                branch = row.branch,
                category = row.category,
                wasteRisk = row.wasteRisk,
                level = row.level,
                salesOpp = row.salesOpp,
                aiCluster = row.aiCluster,
                isCritical = row.isCritical
            )
        }
    }
}

@Composable
fun ForecastRow(
    branch: String,
    category: String,
    wasteRisk: String,
    level: String,
    salesOpp: String,
    aiCluster: String,
    isCritical: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isCritical) Color(0x0DD42721) else Color.Transparent)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(branch, style = Typography.l1, modifier = Modifier.weight(1f))
        Text(category, style = Typography.l2m, modifier = Modifier.weight(1f))
        Text(wasteRisk, style = Typography.l2m, modifier = Modifier.weight(1f))
        Box(modifier = Modifier.weight(1f)) {
            when (level) {
                "Critical" -> StatusBadge("Critical", Color(0xFF92301B), Color(0xFFF9E5DC))
                "High" -> StatusBadge("High", Color(0xFF7B550B), Color(0xFFFBECC8))
                "Medium" -> StatusBadge("Medium", Color(0xFF1C4EA3), Color(0xFFE7F0FF))
            }
        }
        Text(salesOpp, style = Typography.l2m, modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.weight(0.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(aiCluster, style = Typography.l2m)
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun FilterDropdown(text: String) {
    Row(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(256.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text, style = Typography.l1m)
        Icon(
            Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun DashboardLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F6F6))
            .padding(vertical = 40.dp)
    ) {
        TopBarShimmer()

        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RoundedCornerShape(24.dp)
        )

        Spacer(Modifier.height(40.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            repeat(5) {
                ShimmerBox(
                    modifier = Modifier
                        .size(140.dp, 120.dp),
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }

        Spacer(Modifier.height(40.dp))

        ForecastTableShimmer()
    }
}

@Composable
private fun ForecastTableShimmer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShimmerBox(
                modifier = Modifier
                    .width(180.dp)
                    .height(24.dp)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(4) {
                    ShimmerBox(
                        modifier = Modifier
                            .width(120.dp)
                            .height(36.dp),
                        shape = RoundedCornerShape(256.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(Color.Black.copy(alpha = 0.04f))
        )

        repeat(5) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(6) { index ->
                    ShimmerBox(
                        modifier = Modifier
                            .weight(if (index == 5) 0.8f else 1f)
                            .height(18.dp)
                    )
                    if (index != 5) {
                        Spacer(Modifier.width(20.dp))
                    }
                }
            }
        }
    }
}

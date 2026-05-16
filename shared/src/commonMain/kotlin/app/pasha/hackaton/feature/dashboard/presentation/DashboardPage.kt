package app.pasha.hackaton.feature.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
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
import app.pasha.hackaton.ui.kit.component.ShimmerBox
import app.pasha.hackaton.ui.kit.component.StatusBadge
import app.pasha.hackaton.ui.kit.component.TopBar
import app.pasha.hackaton.ui.kit.component.TopBarShimmer

@Composable
fun DashboardPage(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        DashboardState.Loading -> DashboardLoading()
        is DashboardState.Ready -> DashboardContent(currentState)
    }
}

@Composable
private fun DashboardContent(state: DashboardState.Ready) {
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
            OpportunityCard(
                amount = state.opportunity,
                fraction = state.opportunityFraction,
                growth = state.opportunityGrowth
            )
            AtRiskCard(state.atRiskItem)
            WasteCard(predictedWaste = state.predictedWaste)
        }

        Spacer(Modifier.size(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )

        Spacer(Modifier.size(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            state.actionCards.forEach { card ->
                ActionCard(
                    items = card.items,
                    amount = card.amount,
                    title = card.title,
                    tag = card.tag,
                    color = card.color,
                    backgroundColor = card.backgroundColor
                )
            }
        }

        Spacer(Modifier.size(20.dp))

        ForecastTable(rows = state.forecastRows)

        Spacer(Modifier.size(48.dp))
    }
}

@Composable
private fun DashboardLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F6F6))
            .padding(horizontal = 56.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopBarShimmer()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ShimmerBox(
                modifier = Modifier
                    .width(500.dp)
                    .height(256.dp),
                shape = RoundedCornerShape(24.dp)
            )
            ShimmerBox(
                modifier = Modifier.size(256.dp),
                shape = RoundedCornerShape(24.dp)
            )
            ShimmerBox(
                modifier = Modifier.size(256.dp),
                shape = RoundedCornerShape(24.dp)
            )
        }

        Spacer(Modifier.size(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )

        Spacer(Modifier.size(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            repeat(3) {
                ShimmerBox(
                    modifier = Modifier
                        .width(330.dp)
                        .height(184.dp),
                    shape = RoundedCornerShape(24.dp)
                )
            }
        }

        Spacer(Modifier.size(20.dp))

        ForecastTableShimmer()

        Spacer(Modifier.size(48.dp))
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

@Composable
fun OpportunityCard(amount: String, fraction: String, growth: String) {
    Column(
        modifier = Modifier
            .width(500.dp)
            .height(256.dp)
            .background(Color(0xFF181818), RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Sales opportunity - Next 30 days", style = Typography.l1, color = Color.White.copy(alpha = 0.5f))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(amount, style = Typography.h0, color = Color.White)
                Text(fraction, style = Typography.h1, color = Color.White)
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFE2ECD1), RoundedCornerShape(256.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(growth, color = Color(0xFF3A5A28), style = Typography.l1m)
                }
                Text("if all recommended actions applied", style = Typography.l1m, color = Color.White)
            }
        }
    }
}

@Composable
fun AtRiskCard(item: AtRiskItem) {
    Column(
        modifier = Modifier
            .size(256.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("At risk items", style = Typography.l1, color = Color.Black.copy(alpha = 0.5f))
            Row(verticalAlignment = Alignment.Bottom) {
                Text("${item.trackedCount} ", style = Typography.h0)
                Text("of ${item.totalCount} tracked", style = Typography.l2m, color = Color.Black.copy(alpha = 0.5f))
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AtRiskRow("Critical", item.criticalCount, Color(0xFF92301B), Color(0xFFF9E5DC))
            AtRiskRow("High", item.highCount, Color(0xFF7B550B), Color(0xFFFBECC8))
            AtRiskRow("Medium", item.mediumCount, Color(0xFF1C4EA3), Color(0xFFE7F0FF))
        }
    }
}

@Composable
fun AtRiskRow(label: String, count: Int, color: Color, backgroundColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatusBadge(label, color, backgroundColor)
        Text(count.toString(), style = Typography.l1m)
    }
}

@Composable
fun WasteCard(predictedWaste: String) {
    Column(
        modifier = Modifier
            .size(256.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Predicted waste", style = Typography.l1, color = Color.Black.copy(alpha = 0.5f))
            Text(predictedWaste, style = Typography.h0)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .background(Color(0xFFF9E5DC), RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
        )
    }
}

@Composable
fun ActionCard(items: Int, amount: String, title: String, tag: String, color: Color, backgroundColor: Color) {
    Column(
        modifier = Modifier
            .width(330.dp)
            .height(184.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            StatusBadge("$items items", color, backgroundColor)
            Text(amount, style = Typography.l1m, color = Color.Black.copy(alpha = 0.5f))
        }

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(title, style = Typography.s1)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(tag, style = Typography.l1m, color = Color(0xFF1C4EA3))
                Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp), tint = Color(0xFF1C4EA3))
            }
        }
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
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Forecast - June 2026", style = Typography.l1m)
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.1f), RoundedCornerShape(256.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("${rows.size} results", style = Typography.c1)
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FilterDropdown("All branches")
                FilterDropdown("All categories")
                FilterDropdown("High, Critical")
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(256.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Reset", style = Typography.l1m)
                        Icon(Icons.Default.FilterList, contentDescription = null, modifier = Modifier.size(20.dp))
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
            val columns = listOf("Branch", "Category", "Waste risk", "Level", "Sales opp", "AI cluster")
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
    isCritical: Boolean = false
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
        Row(modifier = Modifier.weight(0.8f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(aiCluster, style = Typography.l2m)
            Icon(Icons.Default.ChevronRight, contentDescription = null, modifier = Modifier.size(20.dp))
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
        Icon(Icons.Default.ArrowDropDown, contentDescription = null, modifier = Modifier.size(20.dp))
    }
}

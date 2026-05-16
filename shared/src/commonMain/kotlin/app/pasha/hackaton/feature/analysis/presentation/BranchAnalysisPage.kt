package app.pasha.hackaton.feature.analysis.presentation

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
fun BranchAnalysisPage(viewModel: BranchAnalysisViewModel) {
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(256.dp))
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Text(state.selectedPeriod, style = Typography.l1)
            }
        }

        Spacer(Modifier.height(20.dp))

        MetricSection(title = "Risky branches", items = state.riskyBranches)

        Spacer(Modifier.height(20.dp))

        MetricSection(title = "Sales opportunity", items = state.salesOpportunities)

        Spacer(Modifier.height(48.dp))
    }
}

@Composable
fun MetricSection(title: String, items: List<BranchMetricItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(title, style = Typography.h4)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items.forEach { item ->
                MetricRow(item)
            }
        }
    }
}

@Composable
fun MetricRow(item: BranchMetricItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(81.dp)
    ) {
        Text(
            text = item.name,
            style = Typography.l1m,
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.width(80.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
                .background(item.secondaryColor, RoundedCornerShape(8.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(item.progress)
                    .background(item.primaryColor, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (item.primaryColor != Color(0xFF2970DB) || item.progress > 0.5f) {
                     Text(item.valueLabel, style = Typography.l1m, color = Color.White)
                }
            }
            
            if (item.primaryColor == Color(0xFF2970DB) && item.progress <= 0.5f) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(item.valueLabel, style = Typography.l1m, color = Color(0xFF717171))
                }
            } else if (item.primaryColor != Color(0xFF2970DB) && item.progress <= 0.1f) {
                 // Fallback for very small progress where white text might not fit
            }
        }
    }
}

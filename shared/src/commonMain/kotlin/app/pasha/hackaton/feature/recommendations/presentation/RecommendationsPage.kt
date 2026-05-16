package app.pasha.hackaton.feature.recommendations.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.pasha.hackaton.ui.kit.Typography
import app.pasha.hackaton.ui.kit.component.ConfirmationDialog
import app.pasha.hackaton.ui.kit.component.SecondaryButton
import app.pasha.hackaton.ui.kit.component.StatusBadge
import app.pasha.hackaton.ui.kit.component.TopBar

@Composable
fun RecommendationsPage(viewModel: RecommendationsViewModel) {
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
            horizontalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            VenueSelector("Venue")
            VenueSelector("Sumgayit")
        }

        Spacer(Modifier.height(20.dp))

        ImpactCard(
            sales = state.totalImpactSales,
            fraction = state.totalImpactSalesFraction,
            wasteReduction = state.wasteReduction
        )

        Spacer(Modifier.height(20.dp))

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )

        Spacer(Modifier.height(20.dp))

        state.recommendations.forEach { recommendation ->
            RecommendationCard(recommendation, onApply = { viewModel.onApply(recommendation.id) })
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(48.dp))
    }

    state.selectedRecommendation?.let { recommendation ->
        ConfirmationDialog(
            onDismissRequest = viewModel::onDismissDialog,
            title = "Apply ${recommendation.title.lowercase()}?",
            description = recommendation.description + " The action will be logged and reversible for 24h.",
            icon = when (recommendation.type) {
                RecommendationType.DISCOUNT -> "%"
                else -> "@"
            },
            iconBackground = if (recommendation.type == RecommendationType.DISCOUNT) Color(0xFF2970DB) else Color(0xFF2970DB),
            expectedSales = recommendation.sales,
            expectedSalesSub = "+1.4% vs May",
            wasteReduction = recommendation.waste,
            wasteReductionSub = "Predicted",
            branch = "Sumgayit",
            onCancel = viewModel::onDismissDialog,
            onApply = viewModel::onConfirmApply
        )
    }
}

@Composable
fun VenueSelector(text: String) {
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(256.dp))
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text, style = Typography.l1)
            if (text == "Venue") {
                 Icon(Icons.Default.ArrowDropDown, contentDescription = null, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun ImpactCard(sales: String, fraction: String, wasteReduction: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF181818), RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text("Combined potential impact", style = Typography.l1, color = Color.White.copy(alpha = 0.5f))
        
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("30 day sales", style = Typography.l1m, color = Color.White.copy(alpha = 0.4f))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(sales, style = Typography.h0, color = Color.White, fontSize = 36.sp)
                    Text(fraction, style = Typography.h1, color = Color.White, fontSize = 32.sp)
                }
            }
            
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Waste reduction", style = Typography.l1m, color = Color.White.copy(alpha = 0.4f))
                Text(wasteReduction, style = Typography.h0, color = Color.White, fontSize = 36.sp)
            }
        }
    }
}

@Composable
fun RecommendationCard(item: RecommendationItem, onApply: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    if (item.type == RecommendationType.DISCOUNT) Color(0xFF2970DB) else Color(0xFFF7F6F6),
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (item.type) {
                    RecommendationType.DISCOUNT -> "%"
                    else -> "@"
                },
                style = Typography.h4,
                color = if (item.type == RecommendationType.DISCOUNT) Color.White else Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(40.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(item.title, style = Typography.l1m)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        item.tags.forEach { tag ->
                            StatusBadge(tag.label, tag.color, tag.backgroundColor)
                        }
                    }
                }
                Text(item.description, style = Typography.s2, color = Color.Black.copy(alpha = 0.5f))
            }

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Sales", style = Typography.l2, color = Color.Black.copy(alpha = 0.5f))
                    Text(item.sales, style = Typography.s1)
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Waste", style = Typography.l2, color = Color.Black.copy(alpha = 0.5f))
                    Text(item.waste, style = Typography.s1)
                }
            }
        }

        SecondaryButton(
            onClick = onApply,
            modifier = Modifier.align(Alignment.Bottom)
        ) {
            Text("Apply", style = Typography.l1)
        }
    }
}

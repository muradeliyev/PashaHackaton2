package app.pasha.hackaton.ui.kit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import app.pasha.hackaton.ui.kit.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationDialog(
    onDismissRequest: () -> Unit,
    title: String,
    description: String,
    icon: String,
    iconBackground: Color,
    expectedSales: String,
    expectedSalesSub: String,
    wasteReduction: String,
    wasteReductionSub: String,
    branch: String,
    onCancel: () -> Unit,
    onApply: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .width(600.dp)
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(iconBackground, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = icon,
                        style = Typography.h4,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Confirm action",
                        style = Typography.l2,
                        color = Color.Black.copy(alpha = 0.4f)
                    )
                    Text(
                        text = title,
                        style = Typography.l1m,
                        color = Color.Black
                    )
                }
            }

            // Description
            Text(
                text = description,
                style = Typography.l1m,
                color = Color.Black
            )

            // Metrics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                MetricCard(
                    modifier = Modifier.weight(1f),
                    label = "Expected sales",
                    value = expectedSales,
                    valueColor = Color(0xFF5A8740),
                    subValue = expectedSalesSub
                )
                MetricCard(
                    modifier = Modifier.weight(1f),
                    label = "Waste reduction",
                    value = wasteReduction,
                    valueColor = Color(0xFF92301B),
                    subValue = wasteReductionSub
                )
            }

            // Branch
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Apply for branch",
                    style = Typography.l2,
                    color = Color.Black.copy(alpha = 0.4f)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7F6F6), RoundedCornerShape(24.dp))
                        .padding(24.dp)
                ) {
                    Text(text = branch, style = Typography.l1m)
                }
            }

            // Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SecondaryButton(onClick = onCancel) {
                    Text("Cancel", style = Typography.l1)
                }
                MainButton(
                    onClick = onApply,
                    modifier = Modifier.width(120.dp),
                    content = {
                        Text("Apply", color = Color.White)
                    }
                )
            }
        }
    }
}

@Composable
private fun MetricCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    valueColor: Color,
    subValue: String
) {
    Column(
        modifier = modifier
            .height(184.dp)
            .background(Color(0xFFF7F6F6), RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = Typography.l1m,
            color = Color.Black.copy(alpha = 0.5f)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = value,
                style = Typography.h4,
                fontSize = 20.sp,
                color = valueColor
            )
            Text(
                text = subValue,
                style = Typography.l2,
                color = Color.Black.copy(alpha = 0.5f)
            )
        }
    }
}

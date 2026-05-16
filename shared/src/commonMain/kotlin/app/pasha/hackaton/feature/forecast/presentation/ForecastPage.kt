package app.pasha.hackaton.feature.forecast.presentation

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.pasha.hackaton.ui.kit.Typography
import app.pasha.hackaton.ui.kit.component.ShimmerBox
import app.pasha.hackaton.ui.kit.component.TopBar
import app.pasha.hackaton.ui.kit.component.TopBarShimmer

@Composable
fun ForecastPage(viewModel: ForecastViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        ForecastState.Loading -> ForecastLoading()
        is ForecastState.Ready -> ForecastContent(currentState)
    }
}

@Composable
private fun ForecastContent(state: ForecastState.Ready) {
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
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VenueSelector("Venue")
            VenueSelector(state.selectedVenue)
        }

        Spacer(Modifier.size(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            state.cards.forEach { card ->
                ForecastCard(card)
            }
        }

        Spacer(Modifier.size(20.dp))

        ChartSection()

        Spacer(Modifier.size(48.dp))
    }
}

@Composable
private fun ForecastLoading() {
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
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ShimmerBox(
                modifier = Modifier
                    .width(124.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp)
            )
            ShimmerBox(
                modifier = Modifier
                    .width(156.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp)
            )
        }

        Spacer(Modifier.size(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            repeat(4) {
                ShimmerBox(
                    modifier = Modifier
                        .width(230.dp)
                        .height(184.dp),
                    shape = RoundedCornerShape(24.dp)
                )
            }
        }

        Spacer(Modifier.size(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ShimmerBox(
                    modifier = Modifier
                        .width(160.dp)
                        .height(16.dp)
                )
                ShimmerBox(
                    modifier = Modifier
                        .width(220.dp)
                        .height(24.dp)
                )
            }
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = RoundedCornerShape(8.dp)
            )
        }

        Spacer(Modifier.size(48.dp))
    }
}

@Composable
fun VenueSelector(text: String) {
    Box(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(28.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text, style = Typography.l1)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
    }
}

@Composable
fun ForecastCard(item: ForecastCardItem) {
    Column(
        modifier = Modifier
            .width(230.dp)
            .height(184.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(item.title, style = Typography.l1m, color = Color.Black.copy(alpha = 0.5f))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(item.value, style = Typography.s1, color = item.valueColor)
            Text(item.subtitle, style = Typography.l2m, color = Color.Black.copy(alpha = 0.5f))
        }
    }
}

@Composable
fun ChartSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Waste % - Last 12 months", style = Typography.l2m, color = Color.Black.copy(alpha = 0.5f))
            Text("Trend & forecast", style = Typography.s1)
        }

        // Placeholder for Chart
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Black.copy(alpha = 0.02f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Chart Area Placeholder", color = Color.Gray)
        }
    }
}

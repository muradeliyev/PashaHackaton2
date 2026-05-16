package app.pasha.hackaton.ui.kit.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.pasha.hackaton.ui.kit.Typography

@Composable
fun Sidebar(
    items: List<String>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(256.dp)
            .fillMaxHeight()
            .padding(vertical = 48.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items.forEachIndexed { index, title ->
                SidebarItem(
                    title = title,
                    isSelected = index == selectedIndex,
                    onClick = { onItemClick(index) }
                )
            }
        }

        SecondaryButton(onClick = onLogout) {
            Text("Log out", style = Typography.l1)
        }
    }
}

@Composable
fun SidebarItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = title,
        style = if (isSelected) Typography.h4 else Typography.s1,
        color = if (isSelected) Color.Black else Color.Black.copy(alpha = 0.4f),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(8.dp)
    )
}

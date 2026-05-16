package app.pasha.hackaton.ui.kit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.pasha.hackaton.ui.kit.Typography

@Composable
fun TopBar(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, bottom = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Menu, contentDescription = null)
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .background(Color.White, RoundedCornerShape(28.dp))
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("Search", style = Typography.l1, color = Color.Black.copy(alpha = 0.5f))
        }

        Box(
            modifier = Modifier
                .size(56.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Notifications, contentDescription = null)
        }

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(43.dp)
                .background(Color.Black.copy(alpha = 0.1f))
        )

        Box(
            modifier = Modifier
                .height(56.dp)
                .background(Color.White, RoundedCornerShape(28.dp))
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(userName, style = Typography.l1)
        }
    }
}

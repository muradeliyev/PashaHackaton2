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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import app.pasha.hackaton.ui.kit.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDialog(
    title: String = "Error",
    message: String,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                style = Typography.h3,
                color = Color(0xFFD42721) // Red color for error
            )
            
            Text(
                text = message,
                style = Typography.l1,
                color = Color.Black.copy(alpha = 0.7f)
            )

            MainButton(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.End).width(80.dp),
                content = {
                    Text("Ok", color = Color.White)
                }
            )
        }
    }
}

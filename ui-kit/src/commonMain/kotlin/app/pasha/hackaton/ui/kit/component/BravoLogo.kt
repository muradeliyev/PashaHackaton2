package app.pasha.hackaton.ui.kit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun BrandLogo(
    modifier: Modifier = Modifier,
    painter: Painter,
) {
    Row(
        modifier = modifier
            .padding(48.dp)
            .background(Color(0xffFFFFFF), shape = RoundedCornerShape(28.dp))
            .padding(vertical = 10.dp, horizontal = 20.dp),
        content = {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.height(24.dp)
            )
        }
    )
}
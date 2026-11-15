package hu.ait.tote.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import hu.ait.tote.ui.theme.ToteTheme


@Composable
fun MetricsHeader(
    itemsLeft: Int,
    toSpend: Long,
    total: Long,
    modifier: Modifier = Modifier,
    formatPrice: (Long) -> Unit
) {
    val highlight = ToteTheme.colors.highlighter

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$itemsLeft ${if (itemsLeft == 1) "thing" else "things"}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.drawBehind {
                    drawRoundRect(
                        color = highlight,
                        topLeft = Offset(-4.dp.toPx(), size.height * 0.5f),
                        size = Size(size.width + 8.dp.toPx(), size.height * 0.42f),
                        cornerRadius = CornerRadius(4.dp.toPx())
                    )
                }
            )
            Text(
                text = " left to grab",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Text(
            text = "${formatPrice(toSpend)} to spend · ${formatPrice(total)} total",
            style = MaterialTheme.typography.bodyMedium,
            color = LocalContentColor.current.copy(alpha = 0.6f)
        )
    }
}
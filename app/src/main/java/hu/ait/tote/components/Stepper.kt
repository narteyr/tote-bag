package hu.ait.tote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hu.ait.tote.R
import hu.ait.tote.ui.theme.PriceStyle


@Composable
fun Stepper(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    val content = LocalContentColor.current

    Row(
        modifier = Modifier
            .height(36.dp)
            .clip(CircleShape)
            .background(content.copy(alpha = 0.08f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {onQuantityChange(quantity - 1)}
        ) {
            Icon(Icons.Rounded.Remove,
                contentDescription = "Decrease quantity",
                Modifier.size(24.dp))
        }


        Text(quantity.toString(), style = PriceStyle, textAlign = TextAlign.Center)


        IconButton(
            onClick = {onQuantityChange(quantity + 1)}
        ) {
            Icon(Icons.Rounded.Add,
                contentDescription = stringResource(R.string.increase_quantity),
                Modifier.size(24.dp))
        }
    }
}

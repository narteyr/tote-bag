package hu.ait.tote.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import hu.ait.tote.data.Category
import hu.ait.tote.data.Item
import hu.ait.tote.ui.theme.CategoryColors
import hu.ait.tote.ui.theme.PriceStyle
import hu.ait.tote.ui.theme.ToteTheme


@Composable
fun ItemCard(
    item: Item,
    onEditItem: (Item) -> Unit,
    onDeleteItem: (Item) -> Unit,
    onCheckItem: (Boolean) -> Unit,
    onQuantityChange: (quantity: Int) -> Unit
) {
    // match the category with its respective colors
    val catTheme: CategoryColors = when(item.category) {
        Category.FOOD -> ToteTheme.colors.food
        Category.ELECTRONICS -> ToteTheme.colors.electronics
        Category.HEALTH -> ToteTheme.colors.health
        Category.BOOKS -> ToteTheme.colors.books
        Category.HOME -> ToteTheme.colors.home
    }

    val containerColor = if (item.status) {
        ToteTheme.colors.boughtCard
    } else {catTheme.container}

    Card(
        onClick = {onEditItem(item)},
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = catTheme.content
        ),
        elevation = cardElevation(0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

        ) {
            //up-side
            Row(
                modifier = Modifier
            ) {
                //left-side
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    //name
                    Text(item.name, style = MaterialTheme.typography.titleMedium)

                    //description
                    Text(item.description, style = MaterialTheme.typography.bodySmall)
                }
                //right-side
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    DeleteIconButton { onDeleteItem(item) }
                    CheckCircle (item.status) { onCheckItem(it) }
                }

            }
            Spacer(Modifier.height(8.dp))
            //bottom-side
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Stepper( item.quantity) { onQuantityChange(it) }
                Text((item.quantity * item.price).toPriceString(item.currency), style = PriceStyle)
            }
        }

    }
}


@Composable
fun EditButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Rounded.Edit,
            contentDescription = "Edit item",
            modifier = Modifier.size(20.dp),
            tint = LocalContentColor.current.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun DeleteIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = {onClick()}) {
        Icon(
            imageVector = Icons.Rounded.DeleteOutline,
            tint = LocalContentColor.current.copy(alpha = 0.7f),
            contentDescription = "delete item"
        )
    }
}



@Composable
fun CheckCircle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val colors = ToteTheme.colors
    val fill by animateColorAsState(
        if (checked) colors.checkedContainer else Color.Transparent,
        label = "checkFill"
    )
    val ring by animateColorAsState(
        if (checked) colors.checkedContainer
        else LocalContentColor.current.copy(alpha = 0.35f),
        label = "checkRing"
    )
    val tickScale by animateFloatAsState(
        if (checked) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 400f),
        label = "tickScale"
    )

    Box(
        modifier = Modifier
            .size(48.dp)
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                role = Role.Checkbox,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = 20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(fill)
                .border(3.dp, ring, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = colors.checkedContent,
                modifier = Modifier
                    .size(18.dp)
                    .graphicsLayer { scaleX = tickScale; scaleY = tickScale }
            )
        }
    }
}


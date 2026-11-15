package hu.ait.tote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hu.ait.tote.R
import hu.ait.tote.ui.theme.ToteTheme


@Composable
fun NewItemFab(
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = {onClick()},
        containerColor = ToteTheme.colors.fabContainer,
        contentColor = ToteTheme.colors.fabContent,
        shape = CircleShape,
        text = { Text(stringResource(R.string.new_item), style = MaterialTheme.typography.labelLarge)},
        icon = {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(ToteTheme.colors.fabIconTile),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Rounded.Add,
                    tint = ToteTheme.colors.fabIcon,
                    contentDescription = stringResource(R.string.add_new_item)
                )
            }
        }
    )
}
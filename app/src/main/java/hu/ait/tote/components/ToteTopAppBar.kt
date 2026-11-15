package hu.ait.tote.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hu.ait.tote.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToteTopAppBar(
    onDeleteAll: () -> Unit
) {
    TopAppBar(
        title = {
            Row() {
                Image(painterResource(R.drawable.ic_tote_logo), stringResource(R.string.tote_logo))
                Spacer(Modifier.width(10.dp))
                Text(stringResource(R.string.weekly_shop), style = MaterialTheme.typography.titleLarge)
            }
        },
        actions = {
            IconButton(
                onClick = {onDeleteAll()}
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "delete all items"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
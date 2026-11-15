package hu.ait.tote.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hu.ait.tote.data.Category
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun CustomSplashScreen(
    onTimeOut: () -> Unit
) {
    val categories = Category.entries
    var index by rememberSaveable{
        mutableIntStateOf(0)
    }

    //wait three seconds and call delay complete
    LaunchedEffect(Unit) {
        val stepMs = 3000L / categories.size

        while( index < categories.size - 1) {
            delay(stepMs)
            index++
        }
        delay(stepMs)
        onTimeOut()
    }

    Scaffold() {
        innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(categories[index].iconRes),
                contentDescription = categories[index].name,
                modifier = Modifier.size(124.dp)
            )
        }
    }

}
package hu.ait.tote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import hu.ait.tote.ui.theme.ToteTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import hu.ait.tote.navigation.ShoppingListRoute
import androidx.navigation3.runtime.entryProvider
import dagger.hilt.android.AndroidEntryPoint
import hu.ait.tote.navigation.SplashScreenRoute
import hu.ait.tote.screen.shoppinglist.ShoppingListScreen
import hu.ait.tote.screen.splash.CustomSplashScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToteTheme {
                NavGraph()
            }
        }
    }
}


@Composable
fun NavGraph() {

    val backStack =  rememberNavBackStack(SplashScreenRoute)

    return NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<ShoppingListRoute> {
                ShoppingListScreen()
            }

            entry<SplashScreenRoute> {
                CustomSplashScreen() {
                    backStack.removeLastOrNull()
                    backStack.add(ShoppingListRoute)
                }
            }
        }
    )
}


package hu.ait.tote.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = Grove,
    onPrimary = Color.White,
    primaryContainer = TonalLight,
    onPrimaryContainer = Grove,
    secondary = Citrus,
    onSecondary = Ink,
    secondaryContainer = Citrus,
    onSecondaryContainer = Ink,
    tertiary = Ink,
    onTertiary = Color.White,
    background = Oat,
    onBackground = Ink,
    surface = Oat,
    onSurface = Ink,
    surfaceVariant = BoughtLight,
    onSurfaceVariant = Moss,
    surfaceContainerLowest = SurfaceLight,
    surfaceContainerLow = SheetLight,     // bottom sheets
    surfaceContainer = SheetLight,
    surfaceContainerHigh = SheetLight,    // dialogs
    surfaceContainerHighest = ChipLight,
    outline = Pebble,
    outlineVariant = Line,
    error = Ember,
    onError = Color.White,
    errorContainer = EmberContainer,
    onErrorContainer = Color(0xFF7A1A12),
    scrim = Ink,
)

private val DarkColors = darkColorScheme(
    primary = Citrus,
    onPrimary = Night,
    primaryContainer = TonalDark,
    onPrimaryContainer = Citrus,
    secondary = Citrus,
    onSecondary = Night,
    secondaryContainer = TonalDark,
    onSecondaryContainer = Citrus,
    tertiary = Citrus,
    onTertiary = Night,
    background = Night,
    onBackground = Mist,
    surface = Night,
    onSurface = Mist,
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = Sage,
    surfaceContainerLowest = Night,
    surfaceContainerLow = RaisedDark,
    surfaceContainer = SurfaceDark,
    surfaceContainerHigh = RaisedDark,
    surfaceContainerHighest = LineDark,
    outline = OutlineDark,
    outlineVariant = LineDark,
    error = EmberLight,
    onError = Night,
    errorContainer = EmberContainerDark,
    onErrorContainer = Color(0xFFFFB4A9),
    scrim = Color.Black,
)

val ToteShapes = Shapes(
    extraSmall = RoundedCornerShape(9.dp),   // checkbox
    small = RoundedCornerShape(14.dp),       // icon buttons, chips
    medium = RoundedCornerShape(18.dp),      // buttons, text fields
    large = RoundedCornerShape(24.dp),       // item cards
    extraLarge = RoundedCornerShape(28.dp),  // sheets, dialogs
)

/** A category's tile colours: [container] behind the icon, [content] for the icon and checkbox ring. */
@Immutable
data class CategoryColors(val container: Color, val content: Color)

/** Brand colours that Material's ColorScheme has no slot for. */
@Immutable
data class ToteColors(
    val food: CategoryColors,
    val electronics: CategoryColors,
    val books: CategoryColors,
    val home: CategoryColors,
    val health: CategoryColors,
    val fabContainer: Color,
    val fabContent: Color,
    val fabIconTile: Color,
    val fabIcon: Color,
    val checkedContainer: Color,
    val checkedContent: Color,
    val boughtCard: Color,
    val boughtText: Color,
    val highlighter: Color,
    val cardText: Color,
    val destructive: Color,
    val onDestructive: Color,
    val destructiveIconBg: Color,
)

private val LightToteColors = ToteColors(
    food = CategoryColors(FoodContainerLight, FoodContentLight),
    electronics = CategoryColors(ElectronicsContainerLight, ElectronicsContentLight),
    books = CategoryColors(BooksContainerLight, BooksContentLight),
    home = CategoryColors(HomeContainerLight, HomeContentLight),
    health = CategoryColors(HealthContainerLight, HealthContentLight),
    fabContainer = Ink,
    fabContent = Color.White,
    fabIconTile = Citrus,
    fabIcon = Ink,
    checkedContainer = Grove,
    checkedContent = Citrus,
    boughtCard = BoughtLight,
    boughtText = Color(0xFF5F6763),
    highlighter = Citrus,
    cardText = MossDeep,
    destructive = Ember,
    onDestructive = Color.White,
    destructiveIconBg = EmberIconBg,
)

private val DarkToteColors = ToteColors(
    food = CategoryColors(FoodContainerDark, FoodContentDark),
    electronics = CategoryColors(ElectronicsContainerDark, ElectronicsContentDark),
    books = CategoryColors(BooksContainerDark, BooksContentDark),
    home = CategoryColors(HomeContainerDark, HomeContentDark),
    health = CategoryColors(HealthContainerDark, HealthContentDark),
    fabContainer = Citrus,
    fabContent = Night,
    fabIconTile = Night,
    fabIcon = Citrus,
    checkedContainer = Grove,
    checkedContent = Citrus,
    boughtCard = SurfaceDark,
    boughtText = Color(0xFF85928C),
    highlighter = HighlighterDark,
    cardText = SageLight,
    destructive = EmberButtonDark,
    onDestructive = Night,
    destructiveIconBg = EmberIconBgDark,
)

private val LocalToteColors = staticCompositionLocalOf { LightToteColors }

@Composable
fun ToteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val toteColors = if (darkTheme) DarkToteColors else LightToteColors

    CompositionLocalProvider(LocalToteColors provides toteColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ToteTypography,
            shapes = ToteShapes,
            content = content,
        )
    }
}

/** Access with `ToteTheme.colors.food.container`, alongside `MaterialTheme.colorScheme`. */
object ToteTheme {
    val colors: ToteColors
        @Composable
        @ReadOnlyComposable
        get() = LocalToteColors.current
}

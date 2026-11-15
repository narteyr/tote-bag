package hu.ait.tote.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import hu.ait.tote.R

val Bricolage = FontFamily(
    Font(R.font.bricolage_grotesque_semibold, FontWeight.SemiBold),
    Font(R.font.bricolage_grotesque_bold, FontWeight.Bold),
    Font(R.font.bricolage_grotesque_extrabold, FontWeight.ExtraBold),
)

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
)

val ToteTypography = Typography(
    // Big "things left" counter in the header
    displayLarge = TextStyle(
        fontFamily = Bricolage, fontWeight = FontWeight.ExtraBold,
        fontSize = 80.sp, lineHeight = 72.sp, letterSpacing = (-0.06).em,
        fontFeatureSettings = "tnum",
    ),
    // Empty / done states ("All in the tote.")
    headlineMedium = TextStyle(
        fontFamily = Bricolage, fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp, lineHeight = 34.sp, letterSpacing = (-0.03).em,
    ),
    // Sheet and dialog titles ("New item", "Delete all Food items?")
    headlineSmall = TextStyle(
        fontFamily = Bricolage, fontWeight = FontWeight.Bold,
        fontSize = 24.sp, lineHeight = 30.sp, letterSpacing = (-0.02).em,
    ),
    // Top bar title ("Weekly shop"), amount left to spend
    titleLarge = TextStyle(
        fontFamily = Bricolage, fontWeight = FontWeight.Bold,
        fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = (-0.02).em,
    ),
    // Category section headers ("Food")
    titleMedium = TextStyle(
        fontFamily = Bricolage, fontWeight = FontWeight.Bold,
        fontSize = 20.sp, lineHeight = 26.sp, letterSpacing = (-0.02).em,
    ),
    // Item name on a card
    titleSmall = TextStyle(
        fontFamily = Inter, fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp, lineHeight = 22.sp, letterSpacing = (-0.01).em,
    ),
    bodyLarge = TextStyle(
        fontFamily = Inter, fontWeight = FontWeight.Normal,
        fontSize = 16.sp, lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter, fontWeight = FontWeight.Normal,
        fontSize = 15.sp, lineHeight = 22.sp,
    ),
    // Item description, helper text
    bodySmall = TextStyle(
        fontFamily = Inter, fontWeight = FontWeight.Normal,
        fontSize = 13.sp, lineHeight = 18.sp,
    ),
    // Buttons
    labelLarge = TextStyle(
        fontFamily = Inter, fontWeight = FontWeight.Bold,
        fontSize = 15.sp, lineHeight = 20.sp,
    ),
    // Uppercase section labels, field labels
    labelMedium = TextStyle(
        fontFamily = Inter, fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.08.em,
    ),
    labelSmall = TextStyle(
        fontFamily = Inter, fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp, lineHeight = 14.sp,
    ),
)

val PriceStyle = TextStyle(
    fontFamily = Bricolage, fontWeight = FontWeight.Bold,
    fontSize = 20.sp, letterSpacing = (-0.02).em,
    fontFeatureSettings = "tnum",
)

val PriceSmallStyle = TextStyle(
    fontFamily = Inter, fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp, fontFeatureSettings = "tnum",
)

package `in`.jumbobasket.composescannerappui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ScreenColor,
    primaryVariant = ScreenColor,
    secondary = ItemBackgroundColor
)

private val LightColorPalette = lightColors(
    primary = ScreenColor,
    primaryVariant = ScreenColor,
    secondary = ItemBackgroundColor
)

@Composable
fun ComposeScannerAppUiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
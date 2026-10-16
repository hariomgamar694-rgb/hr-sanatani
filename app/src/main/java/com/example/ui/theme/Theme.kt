package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = RoyalBlue,
    onPrimary = Color.White,
    primaryContainer = SkyBlueLight,
    onPrimaryContainer = DeepNavy,
    secondary = SapphireBlue,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDCE6F5),
    onSecondaryContainer = Color(0xFF09204A),
    tertiary = GoldAccent,
    onTertiary = Color(0xFF261900),
    tertiaryContainer = GoldLight,
    onTertiaryContainer = GoldDark,
    background = PageBackground,
    onBackground = TextPrimary,
    surface = CardBackground,
    onSurface = TextPrimary,
    surfaceVariant = IceBlue,
    onSurfaceVariant = TextSecondary,
    outline = DividerColor
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8BB5FF),
    onPrimary = Color(0xFF002B6D),
    primaryContainer = Color(0xFF10346E),
    onPrimaryContainer = Color(0xFFD6E3FF),
    secondary = Color(0xFFA5C8FE),
    onSecondary = Color(0xFF003063),
    secondaryContainer = Color(0xFF183B70),
    onSecondaryContainer = Color(0xFFD5E3FF),
    tertiary = Color(0xFFFFDF9B),
    onTertiary = Color(0xFF452B00),
    tertiaryContainer = Color(0xFF623F00),
    onTertiaryContainer = Color(0xFFFFDF9B),
    background = Color(0xFF0A1224),
    onBackground = Color(0xFFE2E7F5),
    surface = Color(0xFF111D36),
    onSurface = Color(0xFFE2E7F5),
    surfaceVariant = Color(0xFF1A294A),
    onSurfaceVariant = Color(0xFFC0CADC),
    outline = Color(0xFF33466E)
)

@Composable
fun HRStudyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    HRStudyTheme(darkTheme = darkTheme, content = content)
}

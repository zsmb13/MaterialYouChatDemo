package com.example.matyoudemo

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import io.getstream.chat.android.compose.ui.theme.StreamColors

@Composable
fun createStreamColors(): StreamColors {
    val isInDarkMode = isSystemInDarkTheme()
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val scheme = when {
            isInDarkMode -> dynamicDarkColorScheme(LocalContext.current)
            else -> dynamicLightColorScheme(LocalContext.current)
        }
        return adapt(scheme)
    } else {
        when {
            isInDarkMode -> StreamColors.defaultDarkColors()
            else -> StreamColors.defaultColors()
        }
    }
}

@Composable
private fun adapt(scheme: ColorScheme) = StreamColors(
    textHighEmphasis = scheme.onPrimaryContainer,
    textLowEmphasis = scheme.onSecondaryContainer,
    disabled = scheme.inversePrimary,
    borders = scheme.outline,
    inputBackground = scheme.surfaceVariant,
    appBackground = scheme.background,
    barsBackground = scheme.secondaryContainer,
    linkBackground = scheme.primaryContainer,
    overlay = scheme.surface.copy(alpha = 0.5f),
    overlayDark = scheme.inverseSurface.copy(alpha = 0.5f),
    primaryAccent = scheme.primary,
    errorAccent = scheme.error,
    infoAccent = scheme.secondary,
    highlight = scheme.inversePrimary,
    ownMessagesBackground = scheme.secondaryContainer,
    otherMessagesBackground = scheme.tertiaryContainer,
    deletedMessagesBackground = scheme.onError,
    threadSeparatorGradientStart = scheme.background,
    threadSeparatorGradientEnd = scheme.surfaceVariant,
)

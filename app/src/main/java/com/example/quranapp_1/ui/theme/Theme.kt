package com.example.quranapp_1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// 🎨 الوضع الفاتح
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1B4332),        // أخضر داكن (العناوين + الأزرار)
    secondary = Color(0xFF95D5B2),      // أخضر فاتح (مساعد)
    background = Color(0xFFF9F9F9),     // أبيض كريمي مريح للعين
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// 🌙 الوضع الليلي
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFA7D7C5),        // أخضر ناعم على خلفية داكنة
    secondary = Color(0xFF74C69D),
    background = Color(0xFF121212),     // أسود مائل للرمادي
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun QuranAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // الوضع الليلي حسب النظام
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

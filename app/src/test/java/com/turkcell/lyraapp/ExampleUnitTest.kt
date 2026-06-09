package com.turkcell.lyraapp

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.turkcell.lyraapp.ui.theme.LyraFontFamily
import com.turkcell.lyraapp.ui.theme.Typography
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun typography_usesRobotoFontFamily() {
        assertEquals(FontFamily.SansSerif, LyraFontFamily)
        assertEquals(LyraFontFamily, Typography.bodyLarge.fontFamily)
        assertEquals(LyraFontFamily, Typography.titleLarge.fontFamily)
        assertEquals(LyraFontFamily, Typography.labelSmall.fontFamily)
    }

    @Test
    fun typography_bodyLarge_matchesDocumentation() {
        val bodyLarge = Typography.bodyLarge

        assertEquals(FontWeight.Normal, bodyLarge.fontWeight)
        assertEquals(16.sp, bodyLarge.fontSize)
        assertEquals(24.sp, bodyLarge.lineHeight)
        assertEquals(0.5.sp, bodyLarge.letterSpacing)
    }

    @Test
    fun typography_titleMedium_matchesDocumentation() {
        val titleMedium = Typography.titleMedium

        assertEquals(FontWeight.Medium, titleMedium.fontWeight)
        assertEquals(16.sp, titleMedium.fontSize)
        assertEquals(24.sp, titleMedium.lineHeight)
        assertEquals(0.15.sp, titleMedium.letterSpacing)
    }
}

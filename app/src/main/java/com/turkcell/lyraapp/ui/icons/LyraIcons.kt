package com.turkcell.lyraapp.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp

/**
 * LyraApp ikon seti.
 *
 * Material Icons bağımlılığı eklemeden, ekranların ihtiyaç duyduğu glyph'leri
 * 24x24 viewport'lu [ImageVector] olarak tanımlar. Path'in dolgu rengi önemsizdir;
 * `Icon(...)` composable'ı `tint` ile üzerine yazar. Bu yüzden tüm path'ler
 * [Color.Black] ile doldurulur ve renk daima çağrı tarafında temadan okunur.
 */
object LyraIcons {

    /** Marka logosu: ekolayzer/dalga formu çubukları (Material GraphicEq). */
    val Waveform: ImageVector by lazy {
        lyraIcon(
            name = "Waveform",
            pathData = "M7,18h2V6H7v12zM11,22h2V2h-2v20zM3,14h2v-4H3v4zM15,18h2V6h-2v12zM19,10v4h2v-4h-2z",
        )
    }

    /** Telefon numarası alanının leading ikonu (Material Smartphone, outlined). */
    val Smartphone: ImageVector by lazy {
        lyraIcon(
            name = "Smartphone",
            pathData = "M15.5,1h-8C6.12,1 5,2.12 5,3.5v17C5,21.88 6.12,23 7.5,23h8c1.38,0 " +
                "2.5,-1.12 2.5,-2.5v-17C18,2.12 16.88,1 15.5,1zM13,21h-3v-1h3v1zM16.25,18H6.75V4h9.5V18z",
        )
    }

    /** Şifre alanının leading ikonu (Material Lock). */
    val Lock: ImageVector by lazy {
        lyraIcon(
            name = "Lock",
            pathData = "M18,8h-1V6c0,-2.76 -2.24,-5 -5,-5S7,3.24 7,6v2H6c-1.1,0 -2,0.9 -2,2v10c0," +
                "1.1 0.9,2 2,2h12c1.1,0 2,-0.9 2,-2V10c0,-1.1 -0.9,-2 -2,-2zM12,17c-1.1,0 -2,-0.9 " +
                "-2,-2s0.9,-2 2,-2 2,0.9 2,2 -0.9,2 -2,2zM15.1,8H8.9V6c0,-1.71 1.39,-3.1 3.1,-3.1 " +
                "1.71,0 3.1,1.39 3.1,3.1v2z",
        )
    }

    /** Şifre görünürlük (göz) ikonu (Material Visibility). */
    val Visibility: ImageVector by lazy {
        lyraIcon(
            name = "Visibility",
            pathData = "M12,4.5C7,4.5 2.73,7.61 1,12c1.73,4.39 6,7.5 11,7.5s9.27,-3.11 11,-7.5c-1.73," +
                "-4.39 -6,-7.5 -11,-7.5zM12,17c-2.76,0 -5,-2.24 -5,-5s2.24,-5 5,-5 5,2.24 5,5 -2.24,5 " +
                "-5,5zM12,9c-1.66,0 -3,1.34 -3,3s1.34,3 3,3 3,-1.34 3,-3 -1.34,-3 -3,-3z",
        )
    }

    /** Giriş butonu ileri oku (Material ArrowForward). */
    val ArrowForward: ImageVector by lazy {
        lyraIcon(
            name = "ArrowForward",
            pathData = "M12,4l-1.41,1.41L16.17,11H4v2h12.17l-5.58,5.59L12,20l8,-8z",
        )
    }

    /** Üst bardaki geri oku (Material ArrowBack). */
    val ArrowBack: ImageVector by lazy {
        lyraIcon(
            name = "ArrowBack",
            pathData = "M20,11H7.83l5.59,-5.59L12,4l-8,8 8,8 1.41,-1.41L7.83,13H20v-2z",
        )
    }

    /** Ana sayfa ikonu (Material Home). */
    val Home: ImageVector by lazy {
        lyraIcon(
            name = "Home",
            pathData = "M10,20v-6h4v6h5v-8h3L12,3 2,12h3v8z",
        )
    }

    /** Arama ikonu (Material Search). */
    val Search: ImageVector by lazy {
        lyraIcon(
            name = "Search",
            pathData = "M9.5,3C5.91,3 3,5.91 3,9.5S5.91,16 9.5,16c1.61,0 3.09,-0.59 4.23,-1.57L19.29," +
                "20 20.7,18.59 15.14,13.03C16.1,11.89 16.5,10.45 16.5,9.5C16.5,5.91 13.59,3 9.5,3zM9.5," +
                "5C12,5 14.5,7 14.5,9.5S12,14 9.5,14 5,12 5,9.5 7,5 9.5,5z",
        )
    }

    /** Kütüphane ikonu (Material LibraryMusic). */
    val Library: ImageVector by lazy {
        lyraIcon(
            name = "Library",
            pathData = "M20,2H8c-1.1,0 -2,0.9 -2,2v12c0,1.1 0.9,2 2,2h12c1.1,0 2,-0.9 2,-2V4c0,-1.1 " +
                "-0.9,-2 -2,-2zM18,8h-7v2h7v6h-7V6h7v2zM4,6H2v16c0,1.1 0.9,2 2,2h14v-2H4V6z",
        )
    }

    /** Favoriler ikonu (Material FavoriteBorder). */
    val Favorite: ImageVector by lazy {
        lyraIcon(
            name = "Favorite",
            pathData = "M12.1,18.55l-0.1,0.1 -0.11,-0.1C7.14,14.24 4,11.39 4,8.5 4,6.5 5.5,5 7.5,5c1.54," +
                "0 3.04,0.99 3.57,2.36h1.87C13.46,5.99 14.96,5 16.5,5 18.5,5 20,6.5 20,8.5c0,2.89 " +
                "-3.14,5.74 -7.9,10.05zM16.5,3c-1.74,0 -3.41,0.81 -4.5,2.09C10.91,3.81 9.24,3 7.5,3 " +
                "4.42,3 2,5.42 2,8.5c0,3.76 3.4,6.86 8.55,11.54L12,21.35l1.45,-1.32C18.6,15.36 22," +
                "12.26 22,8.5 22,5.42 19.58,3 16.5,3z",
        )
    }

    /** Profil ikonu (Material PersonOutline). */
    val Person: ImageVector by lazy {
        lyraIcon(
            name = "Person",
            pathData = "M12,12c2.21,0 4,-1.79 4,-4s-1.79,-4 -4,-4 -4,1.79 -4,4 1.79,4 4,4zM12,14c-2.67," +
                "0 -8,1.34 -8,4v2h16v-2c0,-2.66 -5.33,-4 -8,-4zM12,6c1.1,0 2,0.9 2,2s-0.9,2 -2,2 " +
                "-2,-0.9 -2,-2 0.9,-2 2,-2zM6,18c0.22,-0.72 3.31,-2 6,-2 2.7,0 5.8,1.29 6,2H6z",
        )
    }

    /** Gece modu ikonu (Material DarkMode). */
    val Night: ImageVector by lazy {
        lyraIcon(
            name = "Night",
            pathData = "M12,3c-4.97,0 -9,4.03 -9,9s4.03,9 9,9c3.93,0 7.24,-2.52 8.48,-6.03 -0.65,0.19 " +
                "-1.32,0.29 -2.02,0.29 -4.16,0 -7.53,-3.37 -7.53,-7.53 0,-1.54 0.46,-2.97 1.25,-4.16C12.12," +
                "3 12.06,3 12,3zM12,5.1c-0.37,0.84 -0.57,1.74 -0.57,2.63 0,4.99 4.04,9.03 9.03,9.03 " +
                "0.08,0 0.16,0 0.24,-0.01C19.36,18.72 15.92,20 12,20c-4.41,0 -8,-3.59 -8,-8s3.59,-8 8,-8z",
        )
    }
}

/**
 * 24x24 viewport'lu, tek path'li bir [ImageVector] üretir.
 * Path verisi standart SVG/Android `pathData` string formatındadır.
 */
private fun lyraIcon(name: String, pathData: String): ImageVector =
    ImageVector.Builder(
        name = name,
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).addPath(
        pathData = PathParser().parsePathString(pathData).toNodes(),
        fill = SolidColor(Color.Black),
    ).build()

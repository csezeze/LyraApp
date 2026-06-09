# LyraApp - Tipografi Sistemi

> Bu dosya LyraApp uygulamasının tipografi sistemi için tek doğruluk kaynağıdır.
> Tipografi değerleri Android Jetpack Compose Material 3 yapısına uygun şekilde
> `Type.kt` dosyasında tanımlanır.

---

## 1. Temel Kural

LyraApp içinde tipografi stilleri doğrudan `@Composable` ekranlarda oluşturulmaz.
Metin stilleri `MaterialTheme.typography.<slot>` üzerinden kullanılmalıdır.

Ham `TextStyle(...)` tanımı yalnızca `Type.kt` içinde yapılır.

---

## 2. Font Ailesi

LyraApp tipografi sistemi Roboto font ailesini kullanır.
Android sisteminde Roboto varsayılan sans-serif font ailesi olduğu için ekstra font
dosyası eklenmez.

```kotlin
val LyraFontFamily = FontFamily.SansSerif
```

---

## 3. Material 3 Tipografi Rolleri

| Slot | Font | Weight | Size | Line Height | Letter Spacing |
|---|---|---:|---:|---:|---:|
| displayLarge | Roboto | Normal | 57.sp | 64.sp | 0.sp |
| displayMedium | Roboto | Normal | 45.sp | 52.sp | 0.sp |
| displaySmall | Roboto | Normal | 36.sp | 44.sp | 0.sp |
| headlineLarge | Roboto | Normal | 32.sp | 40.sp | 0.sp |
| headlineMedium | Roboto | Normal | 28.sp | 36.sp | 0.sp |
| headlineSmall | Roboto | Normal | 24.sp | 32.sp | 0.sp |
| titleLarge | Roboto | Normal | 22.sp | 28.sp | 0.sp |
| titleMedium | Roboto | Medium | 16.sp | 24.sp | 0.15.sp |
| titleSmall | Roboto | Medium | 14.sp | 20.sp | 0.1.sp |
| bodyLarge | Roboto | Normal | 16.sp | 24.sp | 0.5.sp |
| bodyMedium | Roboto | Normal | 14.sp | 20.sp | 0.25.sp |
| bodySmall | Roboto | Normal | 12.sp | 16.sp | 0.4.sp |
| labelLarge | Roboto | Medium | 14.sp | 20.sp | 0.1.sp |
| labelMedium | Roboto | Medium | 12.sp | 16.sp | 0.5.sp |
| labelSmall | Roboto | Medium | 11.sp | 16.sp | 0.5.sp |

---

## 4. Kullanım Kuralı

Ekranlarda örnek kullanım:

```kotlin
Text(
    text = "LyraApp",
    style = MaterialTheme.typography.titleLarge,
)
```

Ekranlarda aşağıdaki kullanım tercih edilmez:

```kotlin
TextStyle(
    fontSize = 16.sp,
)
```

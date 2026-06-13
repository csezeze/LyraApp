package com.turkcell.lyraapp.ui.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * MVI Route katmanında tek seferlik Effect akışlarını lifecycle-aware şekilde tüketir.
 *
 * Route, ViewModel'den gelen Effect'i burada toplar; Screen katmanı yine yalnızca State
 * ve Intent ile çalışır. Böylece her ekranda aynı `LaunchedEffect + collect` kalıbı
 * tekrar edilmez.
 */
@Composable
fun <Effect> CollectEffect(
    effect: Flow<Effect>,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEffect: suspend (Effect) -> Unit,
) {
    val currentOnEffect by rememberUpdatedState(onEffect)

    LaunchedEffect(effect, lifecycleOwner, minActiveState) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            effect.collect { currentOnEffect(it) }
        }
    }
}

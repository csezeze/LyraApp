package com.turkcell.lyraapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turkcell.lyraapp.data.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ana Sayfa MVI ViewModel'i.
 *
 * İçerikleri [HomeRepository] üzerinden alır; UI yalnızca [onIntent] ile niyet gönderir.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect: Flow<HomeEffect> = _effect.receiveAsFlow()

    init {
        loadContent()
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.LoadContent -> loadContent()
            is HomeIntent.TabSelected -> _uiState.update { it.copy(selectedTab = intent.tab) }
            is HomeIntent.MoodShortcutClicked -> showMessage("Liste hazırlanıyor.")
            is HomeIntent.TrackClicked -> showMessage("Çalma özelliği henüz kapsamda değil.")
            is HomeIntent.PlaylistClicked -> showMessage("Çalma listesi hazırlanıyor.")
            HomeIntent.SeeAllRecentlyPlayedClicked -> showMessage("Tüm son çalınanlar henüz kapsamda değil.")
        }
    }

    private fun loadContent() {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = homeRepository.getHomeContent()
            result
                .onSuccess { content ->
                    _uiState.update {
                        it.copy(
                            greeting = content.greeting,
                            headline = content.headline,
                            moodShortcuts = content.moodShortcuts,
                            recentlyPlayed = content.recentlyPlayed,
                            playlists = content.playlists,
                            isLoading = false,
                            errorMessage = null,
                        )
                    }
                }
                .onFailure { error ->
                    val message = error.message ?: "Ana Sayfa içerikleri yüklenemedi."
                    _uiState.update { it.copy(isLoading = false, errorMessage = message) }
                    _effect.send(HomeEffect.ShowMessage(message))
                }
        }
    }

    private fun showMessage(message: String) {
        viewModelScope.launch { _effect.send(HomeEffect.ShowMessage(message)) }
    }
}

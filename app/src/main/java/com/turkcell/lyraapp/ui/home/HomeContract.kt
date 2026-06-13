package com.turkcell.lyraapp.ui.home

import com.turkcell.lyraapp.data.home.HomeMoodShortcut
import com.turkcell.lyraapp.data.home.HomePlaylist
import com.turkcell.lyraapp.data.home.HomeTrack

/**
 * Ana Sayfa MVI sözleşmesi: State, Intent ve Effect tek dosyada tutulur.
 */
data class HomeUiState(
    val greeting: String = "",
    val headline: String = "",
    val moodShortcuts: List<HomeMoodShortcut> = emptyList(),
    val recentlyPlayed: List<HomeTrack> = emptyList(),
    val playlists: List<HomePlaylist> = emptyList(),
    val selectedTab: HomeTab = HomeTab.Home,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

sealed interface HomeIntent {
    data object LoadContent : HomeIntent
    data class TabSelected(val tab: HomeTab) : HomeIntent
    data class MoodShortcutClicked(val id: String) : HomeIntent
    data class TrackClicked(val id: String) : HomeIntent
    data class PlaylistClicked(val id: String) : HomeIntent
    data object SeeAllRecentlyPlayedClicked : HomeIntent
}

sealed interface HomeEffect {
    data class ShowMessage(val message: String) : HomeEffect
}

enum class HomeTab(val label: String) {
    Home("Ana sayfa"),
    Search("Ara"),
    Library("Kütüphane"),
    Favorites("Favoriler"),
    Profile("Profil"),
}

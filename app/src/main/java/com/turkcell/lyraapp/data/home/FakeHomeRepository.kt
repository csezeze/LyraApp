package com.turkcell.lyraapp.data.home

import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Ana Sayfa için sahte içerik kaynağı.
 *
 * Gerçek bir ağ çağrısı yapmaz; ödev kapsamında MVI state akışını uçtan uca çalıştırmak için
 * kısa bir gecikme ile içerik döndürür.
 */
class FakeHomeRepository @Inject constructor() : HomeRepository {

    override suspend fun getHomeContent(): Result<HomeContent> {
        delay(NETWORK_DELAY_MS)
        return Result.success(
            HomeContent(
                greeting = "İyi akşamlar",
                headline = "Ne dinlemek istersin?",
                moodShortcuts = listOf(
                    HomeMoodShortcut("night-drive", "Gece Sürüşü", HomeArtworkStyle.Purple),
                    HomeMoodShortcut("morning-coffee", "Sabah Kahvesi", HomeArtworkStyle.Blue),
                    HomeMoodShortcut("neon-streets", "Neon Sokaklar", HomeArtworkStyle.Orange),
                    HomeMoodShortcut("focus", "Odaklan", HomeArtworkStyle.Teal),
                    HomeMoodShortcut("deep-blue", "Derin Mavi", HomeArtworkStyle.Green),
                    HomeMoodShortcut("summer-memories", "Yaz Anıları", HomeArtworkStyle.Teal),
                ),
                recentlyPlayed = listOf(
                    HomeTrack("neon-streets-track", "Neon Sokaklar", "Şehir Işıkları", HomeArtworkStyle.Orange),
                    HomeTrack("deep-blue-track", "Derin Mavi", "Okyanus", HomeArtworkStyle.Green),
                    HomeTrack("star-dust-track", "Yıldız Tozu", "Polaris", HomeArtworkStyle.Teal),
                ),
                playlists = listOf(
                    HomePlaylist("night-drive-playlist", "Gece Sürüşü", "Sakin ritimler", HomeArtworkStyle.Purple),
                    HomePlaylist("morning-coffee-playlist", "Sabah Kahvesi", "Güne başla", HomeArtworkStyle.Blue),
                    HomePlaylist("focus-playlist", "Odaklan", "Derin çalışma", HomeArtworkStyle.Teal),
                ),
            ),
        )
    }

    private companion object {
        const val NETWORK_DELAY_MS = 500L
    }
}

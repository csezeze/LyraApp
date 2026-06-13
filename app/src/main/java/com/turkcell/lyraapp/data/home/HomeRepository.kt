package com.turkcell.lyraapp.data.home

/**
 * Ana Sayfa içeriklerinin tek soyutlama noktası.
 *
 * Backend sözleşmesi henüz tanımlı olmadığı için şimdilik [FakeHomeRepository] ile sağlanır.
 * Gerçek API geldiğinde ViewModel ve Contract değişmeden yalnızca implementasyon değişir.
 */
interface HomeRepository {
    suspend fun getHomeContent(): Result<HomeContent>
}

data class HomeContent(
    val greeting: String,
    val headline: String,
    val moodShortcuts: List<HomeMoodShortcut>,
    val recentlyPlayed: List<HomeTrack>,
    val playlists: List<HomePlaylist>,
)

data class HomeMoodShortcut(
    val id: String,
    val title: String,
    val artworkStyle: HomeArtworkStyle,
)

data class HomeTrack(
    val id: String,
    val title: String,
    val artist: String,
    val artworkStyle: HomeArtworkStyle,
)

data class HomePlaylist(
    val id: String,
    val title: String,
    val subtitle: String,
    val artworkStyle: HomeArtworkStyle,
)

enum class HomeArtworkStyle {
    Purple,
    Orange,
    Green,
    Teal,
    Blue,
}

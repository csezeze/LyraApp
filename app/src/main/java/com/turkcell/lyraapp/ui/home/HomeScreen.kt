package com.turkcell.lyraapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turkcell.lyraapp.data.home.HomeArtworkStyle
import com.turkcell.lyraapp.data.home.HomeMoodShortcut
import com.turkcell.lyraapp.data.home.HomePlaylist
import com.turkcell.lyraapp.data.home.HomeTrack
import com.turkcell.lyraapp.ui.icons.LyraIcons
import com.turkcell.lyraapp.ui.theme.LyraAppTheme

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.ShowMessage -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    HomeScreen(
        state = uiState,
        onIntent = viewModel::onIntent,
        snackbarHostState = snackbarHostState,
        modifier = modifier,
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            HomeBottomBar(
                selectedTab = state.selectedTab,
                onTabSelected = { onIntent(HomeIntent.TabSelected(it)) },
            )
        },
    ) { innerPadding ->
        if (state.isLoading && state.moodShortcuts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 18.dp),
            ) {
                HomeHeader(greeting = state.greeting, headline = state.headline)
                Spacer(Modifier.height(18.dp))

                MoodGrid(
                    moods = state.moodShortcuts,
                    onMoodClick = { onIntent(HomeIntent.MoodShortcutClicked(it)) },
                )
                Spacer(Modifier.height(24.dp))

                SectionHeader(
                    title = "Son çalınanlar",
                    action = "Tümü",
                    onActionClick = { onIntent(HomeIntent.SeeAllRecentlyPlayedClicked) },
                )
                Spacer(Modifier.height(12.dp))
                RecentlyPlayedRow(
                    tracks = state.recentlyPlayed,
                    onTrackClick = { onIntent(HomeIntent.TrackClicked(it)) },
                )
                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Senin için çalma listeleri",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(12.dp))
                PlaylistRow(
                    playlists = state.playlists,
                    onPlaylistClick = { onIntent(HomeIntent.PlaylistClicked(it)) },
                )
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun HomeHeader(
    greeting: String,
    headline: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = greeting,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = headline,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = LyraIcons.Night,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp),
            )
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "ZK",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
            }
        }
    }
}

@Composable
private fun MoodGrid(
    moods: List<HomeMoodShortcut>,
    onMoodClick: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        moods.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                rowItems.forEach { mood ->
                    MoodShortcutCard(
                        mood = mood,
                        onClick = { onMoodClick(mood.id) },
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun MoodShortcutCard(
    mood: HomeMoodShortcut,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ArtworkBox(
            style = mood.artworkStyle,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp),
        )
        Text(
            text = mood.title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 10.dp),
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    action: String,
    onActionClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = action,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onActionClick),
        )
    }
}

@Composable
private fun RecentlyPlayedRow(
    tracks: List<HomeTrack>,
    onTrackClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        tracks.forEach { track ->
            Column(
                modifier = Modifier
                    .width(118.dp)
                    .clickable { onTrackClick(track.id) },
            ) {
                ArtworkBox(
                    style = track.artworkStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = track.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = track.artist,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun PlaylistRow(
    playlists: List<HomePlaylist>,
    onPlaylistClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        playlists.forEach { playlist ->
            Column(
                modifier = Modifier
                    .width(136.dp)
                    .clickable { onPlaylistClick(playlist.id) },
            ) {
                ArtworkBox(
                    style = playlist.artworkStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(106.dp)
                        .clip(RoundedCornerShape(12.dp)),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = playlist.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = playlist.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun ArtworkBox(
    style: HomeArtworkStyle,
    modifier: Modifier = Modifier,
) {
    val color = artworkColor(style)
    Box(
        modifier = modifier.background(color),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.10f)),
        )
        Icon(
            imageVector = LyraIcons.Waveform,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.32f),
            modifier = Modifier.size(36.dp),
        )
    }
}

@Composable
private fun artworkColor(style: HomeArtworkStyle): Color =
    when (style) {
        HomeArtworkStyle.Purple -> MaterialTheme.colorScheme.primary
        HomeArtworkStyle.Orange -> MaterialTheme.colorScheme.tertiary
        HomeArtworkStyle.Green -> MaterialTheme.colorScheme.secondary
        HomeArtworkStyle.Teal -> MaterialTheme.colorScheme.inversePrimary
        HomeArtworkStyle.Blue -> MaterialTheme.colorScheme.primaryContainer
    }

@Composable
private fun HomeBottomBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainerLow) {
        HomeTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon(),
                        contentDescription = tab.label,
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                    )
                },
            )
        }
    }
}

private fun HomeTab.icon(): ImageVector =
    when (this) {
        HomeTab.Home -> LyraIcons.Home
        HomeTab.Search -> LyraIcons.Search
        HomeTab.Library -> LyraIcons.Library
        HomeTab.Favorites -> LyraIcons.Favorite
        HomeTab.Profile -> LyraIcons.Person
    }

@Preview(name = "Home - Light", showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenLightPreview() {
    LyraAppTheme(darkTheme = false) {
        HomeScreen(state = previewHomeState(), onIntent = {})
    }
}

@Preview(name = "Home - Dark", showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenDarkPreview() {
    LyraAppTheme(darkTheme = true) {
        HomeScreen(state = previewHomeState(), onIntent = {})
    }
}

private fun previewHomeState(): HomeUiState =
    HomeUiState(
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
    )

package com.malky.bostatask.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malky.bostatask.domain.Genre
import com.malky.bostatask.ui.theme.Background
import com.malky.bostatask.ui.theme.CardBackground
import com.malky.bostatask.ui.theme.TextPrimary

@Composable
fun GamesFilters(
    modifier: Modifier = Modifier,
    filters: List<Genre> = listOf(
        Genre(id = 0, name = "All"),
        Genre(id = 4, name = "Action"),
        Genre(id = 5, name = "RPG"),
        Genre(id = 2, name = "Shooter"),
        Genre(id = 7, name = "Puzzle"),
        Genre(id = 3, name = "Adventure"),
        Genre(id = 51, name = "Indie"),
        Genre(id = 83, name = "Platformer"),
        Genre(id = 59, name = "Massively Multiplayer"),
        Genre(id = 14, name = "Simulation"),
        Genre(id = 40, name = "Casual"),
        Genre(id = 6, name = "Fighting"),
        Genre(id = 10, name = "Strategy"),
    )
) {
    //region internal states
    var selectedFilterIndex by remember { mutableIntStateOf(0) }
    //endregion
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(filters) { index, genre ->
            FilterChip(
                selected = index == selectedFilterIndex,
                onClick = { selectedFilterIndex = index },
                label = {
                    Text(
                        text = genre.name,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (selectedFilterIndex == index) Background else TextPrimary,
                            fontWeight = if (selectedFilterIndex == index) FontWeight.Medium else FontWeight.Normal
                        )
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = CardBackground,
                    selectedContainerColor = TextPrimary,
                    ),
                shape = CircleShape,
                border = null
            )
        }
    }
}


@Preview
@Composable
private fun PreviewGamesFilters() {
    GamesFilters()
}
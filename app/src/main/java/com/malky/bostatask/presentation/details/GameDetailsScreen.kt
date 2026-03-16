package com.malky.bostatask.presentation.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.malky.bostatask.presentation.composables.DetailsPage
import com.malky.bostatask.presentation.composables.HeroPage
import com.malky.bostatask.ui.theme.Background
import kotlinx.coroutines.launch

@Composable
fun GameDetailsScreen() {
    GameDetailsContent()
}


@Composable
private fun GameDetailsContent(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    //region internal states
    val pagerState = rememberPagerState { 2 }
    val coroutineScope = rememberCoroutineScope()
    //endregion

    Scaffold(
        containerColor = Background,
    ) { innerPadding ->
        VerticalPager(
            state = pagerState,
            modifier = modifier
        ) { page ->
            when (page) {
                0 -> HeroPage(
                    onScrollDown = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page + 1)
                        }
                    }
                )

                1 -> DetailsPage()
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewGameDetailsContent() {
    GameDetailsContent()
}

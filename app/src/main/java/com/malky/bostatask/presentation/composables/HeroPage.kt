package com.malky.bostatask.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malky.bostatask.R

@Composable
fun HeroPage(
    onScrollDown: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.gow),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.8f)
                        ),
                        startY = 600f
                    )
                )
        )

        IconButton(
            onClick = { },
            modifier = Modifier
                .statusBarsPadding()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 8.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.65f)
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "10 June 2020",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 24.dp),
                text = "God of War Ragnarok",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 48.sp,
                    lineHeight = 56.sp
                ),
            )

            Spacer(modifier = Modifier.height(52.dp))

            Column(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = onScrollDown
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "more",
                    color = Color.White.copy(alpha = 0.6f),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.6f)
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewHeroPage() {
    HeroPage()
}
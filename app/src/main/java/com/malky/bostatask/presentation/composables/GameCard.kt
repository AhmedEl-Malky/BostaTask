package com.malky.bostatask.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malky.bostatask.R
import com.malky.bostatask.ui.theme.AccentYellow
import com.malky.bostatask.ui.theme.Surface
import com.malky.bostatask.ui.theme.TextPrimary
import com.malky.bostatask.ui.theme.TextSecondary

@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = onClick
            ),
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = painterResource(R.drawable.gow),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 6.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(Surface)
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.rating_star),
                    contentDescription = null,
                    tint = AccentYellow
                )
                Text(
                    text = "4.5",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.sp,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Text(
            text = "God of War Ragnarok",
            style = MaterialTheme.typography.titleMedium.copy(
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Action , Adventure",
            style = MaterialTheme.typography.titleSmall.copy(
                color = TextSecondary,
                fontWeight = FontWeight.Medium,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }
}


@Preview
@Composable
private fun PreviewGameCard() {
    GameCard(
        onClick = {}
    )
}
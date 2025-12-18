package iut.fauryollivier.snoozespot.app.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(
    rating: Float,
    starSize: Dp = 24.dp,
    maxStars: Int = 5,
    filledColor: Color = Color(0xFFFFD700),
    emptyColor: Color = Color.Gray,
    onClicked: (i: Int) -> Unit = {}
) {
    Row {
        for (i in 1..maxStars) {
            val icon = when {
                rating >= i -> Icons.Filled.Star
                rating - i + 1 in 0.5..0.99 -> Icons.Filled.StarHalf
                else -> Icons.Filled.StarBorder
            }
            val tint = if (i <= rating || rating - i + 1 in 0.5..0.99) filledColor else emptyColor
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier
                    .size(starSize)
                    .clickable { onClicked(i) }
            )
        }
    }
}
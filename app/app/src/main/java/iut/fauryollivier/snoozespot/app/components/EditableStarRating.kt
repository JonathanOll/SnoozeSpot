package iut.fauryollivier.snoozespot.app.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState

@Composable
fun EditableStarRating(rating: MutableIntState) {
    StarRating(
        rating.intValue.toFloat(),
        onClicked = { it ->
            rating.intValue = it
        }
    )
}
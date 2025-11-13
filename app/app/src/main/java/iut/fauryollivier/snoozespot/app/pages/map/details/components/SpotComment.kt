package iut.fauryollivier.snoozespot.app.pages.map.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.SpotComment
import iut.fauryollivier.snoozespot.app.components.StarRating

@Composable
fun SpotComment(comment: SpotComment) {
    Column (modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(R.drawable.lobster),
                    stringResource(R.string.profile_picture),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(comment.user.username)
            }

            Spacer(modifier = Modifier.weight(1f))

            StarRating(comment.rating.toFloat())
        }

        Text(comment.description, modifier = Modifier.padding(0.dp, 8.dp))
    }
}
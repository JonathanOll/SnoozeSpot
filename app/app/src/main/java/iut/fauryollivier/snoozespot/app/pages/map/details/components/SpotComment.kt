package iut.fauryollivier.snoozespot.app.pages.map.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.SpotCommentDTO
import iut.fauryollivier.snoozespot.app.components.StarRating
import iut.fauryollivier.snoozespot.app.pages.destinations.AccountDetailsScreenDestination

@Composable
fun SpotComment(
    navigator: DestinationsNavigator,
    comment: SpotCommentDTO
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {  navigator.navigate(
                        AccountDetailsScreenDestination(
                            comment.user.uuid
                        )
                    )
                }
            ) {
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
package iut.fauryollivier.snoozespot.app.pages.friends.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.models.User

@Composable
fun FriendElement(user: User) {

    Row {
        Row {
            Image(
                painterResource(R.drawable.lobster),
                contentDescription = "User's profile picture"
            )
            Text(user.name)
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            Icons.Default.MoreVert,
            contentDescription = "More"
        )
    }

}
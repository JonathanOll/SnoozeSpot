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
import androidx.compose.ui.res.stringResource
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.User

@Composable
fun FriendElement(userModel: User) {

    Row {
        Row {
            Image(
                painterResource(R.drawable.lobster),
                contentDescription = stringResource(R.string.user_profile_picture)
            )
            Text(userModel.username)
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.more)
        )
    }

}
package iut.fauryollivier.snoozespot.app.pages.friends.components

import androidx.compose.foundation.border
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import iut.fauryollivier.snoozespot.app.components.UserProfilePicture

@Composable
fun FriendElement(
    user: UserDTO,
    modifier: Modifier = Modifier
) {

    Row (
        modifier = modifier
            .border(1.dp, Color(0xFFEDEDED))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserProfilePicture(
                user,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(user.username)
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            Icons.Default.MoreVert,
            contentDescription = stringResource(R.string.more)
        )
    }

}
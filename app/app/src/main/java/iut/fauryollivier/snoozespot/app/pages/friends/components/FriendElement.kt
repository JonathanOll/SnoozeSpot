package iut.fauryollivier.snoozespot.app.pages.friends.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    modifier: Modifier = Modifier,
    onUnfollow: (user: UserDTO) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

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

        Box {
            Icon(
                Icons.Default.MoreVert,
                modifier = Modifier
                    .clickable { menuExpanded = !menuExpanded },
                contentDescription = stringResource(R.string.more)
            )

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.unfollow)) },
                    onClick = {
                        menuExpanded = false
                        onUnfollow(user)
                    }
                )
            }
        }

    }

}
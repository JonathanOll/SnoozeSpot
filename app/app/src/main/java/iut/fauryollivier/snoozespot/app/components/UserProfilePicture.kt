package iut.fauryollivier.snoozespot.app.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO

@Composable
fun UserProfilePicture(
    user: UserDTO,
    modifier: Modifier = Modifier,
    expandable: Boolean = false,
    onClickOnProfilePic: (() -> Unit)? = null
) {
    if (user.profilePicture != null)
        if (expandable) {
            ExpandableImage(
                imageUri = NetworkDataSource.BASE_URL + user.profilePicture.path,
                contentDescription = "Avatar de ${user.username}",
                modifier = modifier,
                defaultClickable = onClickOnProfilePic == null
            )
        } else {
            AsyncImage(
                NetworkDataSource.BASE_URL + user.profilePicture.path,
                stringResource(R.string.profile_picture),
                modifier = modifier,
                error = painterResource(R.drawable.default_profile_picture)
            )
        }

    else
        Image(
            painterResource(R.drawable.default_profile_picture),
            stringResource(R.string.profile_picture),
            modifier = modifier
        )
}
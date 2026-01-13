package iut.fauryollivier.snoozespot.app.pages.feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.PostCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.components.ExpandableImageWithDownload
import iut.fauryollivier.snoozespot.app.destinations.AccountDetailsScreenDestination


@Composable
fun FeedElement(
    navigator: DestinationsNavigator,
    postDTO: PostDTO,
    isComment: Boolean = false,
    modifier: Modifier = Modifier,
    onLike: (PostDTO) -> Unit,
    onDelete: (PostDTO) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
                    .border(1.dp, Color(0xFFEDEDED))
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row (
                modifier = Modifier.clickable { navigator.navigate(AccountDetailsScreenDestination(postDTO.user.uuid)) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (postDTO.user.profilePicture != null)
                    AsyncImage(
                        NetworkDataSource.BASE_URL + postDTO.user.profilePicture.path,
                        stringResource(R.string.profile_picture),
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                else
                    Image(
                        painterResource(R.drawable.lobster),
                        stringResource(R.string.profile_picture),
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )

                Spacer(modifier = Modifier.width(8.dp))

                Text(postDTO.user.username)
            }

            Spacer(modifier = Modifier.weight(1f))

            Box {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.more_options),
                    tint = Color(0xFF49454F),
                    modifier = Modifier.clickable {
                        menuExpanded = !menuExpanded
                    }
                )

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.delete)) },
                        onClick = {
                            menuExpanded = false
                            onDelete(postDTO)
                        }
                    )
                }
            }


        }

        Text(
            text = postDTO.content,
            fontSize = 16.sp
        )

        if(!isComment) {
            Spacer(modifier = Modifier.height(8.dp))

            if (postDTO.pictures.isNotEmpty())
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    postDTO.pictures.forEachIndexed { index, it ->
                        ExpandableImageWithDownload(
                            imageUri = NetworkDataSource.BASE_URL + it.path,
                            contentDescription = stringResource(R.string.picture_n, index),
                            modifier = Modifier
                                .size(160.dp)
                                .padding(end = 4.dp)
                        )
                    }
                }


            Spacer(modifier = Modifier.height(8.dp))

            Row {
                OutlinedButton(
                    onClick = { /* TODO() */ },
                    modifier = Modifier
                        .height(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Comment, contentDescription = stringResource(R.string.comment_post),
                        tint = Color(0xFF49454F)
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        stringResource(R.string.comments),
                        color = Color(0xFF49454F),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                OutlinedButton(
                    onClick = { onLike(postDTO) },
                    modifier = Modifier
                        .height(40.dp)
                ) {
                    Text(
                        postDTO.likeCount.toString(),
                        color = Color(0xFF49454F)
                    )

                    Spacer(
                        modifier = Modifier.width(2.dp)
                    )

                    if (postDTO.likedByUser) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = stringResource(R.string.like_post),
                            tint = Color(0xFFE53935)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.like_post),
                            tint = Color(0xFF49454F)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun FeedElement(
    navigator: DestinationsNavigator,
    postComment: PostCommentDTO,
    modifier: Modifier = Modifier,
    onLike: (post: PostDTO) -> Unit = {},
    onDelete: (post: PostDTO) -> Unit = {}
) {
    FeedElement(
        navigator,
        PostDTO(
            -1,
            postComment.user,
            postComment.content,
            -1,
            emptyList(),
            emptyList(),
            false,
            postComment.createdAt
        ),
        isComment = true,
        modifier = modifier,
        onLike = onLike,
        onDelete = onDelete
    )
}
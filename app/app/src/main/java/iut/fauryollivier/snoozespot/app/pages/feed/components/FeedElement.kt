package iut.fauryollivier.snoozespot.app.pages.feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.models.PostModel


@Composable
fun FeedElement(postModel: PostModel) {
    Column(
        modifier = Modifier
            .border(1.dp, Color(0xFFEDEDED))
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(R.drawable.lobster),
                    "Profile picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(postModel.user.name)
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = Color(0xFF49454F)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = postModel.content,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (postModel.medias.isNotEmpty())
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                postModel.medias.forEachIndexed { index, it ->
                    AsyncImage(
                        model = it,
                        contentDescription = "Picture $index",
                        modifier = Modifier
                            .size(160.dp)
                            .padding(end = 4.dp),
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        error = painterResource(id = R.drawable.lobster)
                    )
                }
            }


        Spacer(modifier = Modifier.height(4.dp))

        Row {
            OutlinedButton(
                onClick = { /* TODO() */ },
                modifier = Modifier
                    .height(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Comment, contentDescription = "Comment the post",
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
                onClick = { /* TODO() */ },
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text(
                    "${postModel.likesCount}",
                    color = Color(0xFF49454F)
                )

                Spacer(
                    modifier = Modifier.width(2.dp)
                )

                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Like the post",
                    tint = Color(0xFF49454F)
                )
            }
        }
    }
}
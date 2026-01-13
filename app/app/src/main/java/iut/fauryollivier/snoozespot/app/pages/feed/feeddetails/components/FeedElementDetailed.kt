package iut.fauryollivier.snoozespot.app.pages.feed.feeddetails.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.PostCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.components.AnonymousOnly
import iut.fauryollivier.snoozespot.app.components.AuthOnly
import iut.fauryollivier.snoozespot.app.destinations.NewPostScreenDestination
import iut.fauryollivier.snoozespot.app.pages.feed.components.FeedElement

@Composable
fun FeedElementDetailed(
    navigator: DestinationsNavigator,
    postDTO: PostDTO,
    modifier: Modifier = Modifier,
    onLike: (post: PostDTO) -> Unit = {},
    onDelete: (post: PostDTO) -> Unit = {},
    onCommentDelete: (post: PostCommentDTO) -> Unit = {},
) {
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            FeedElement(
                navigator,
                postDTO,
                onLike = onLike,
                onDelete = onDelete
            )
            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).height(2.dp).border(1.dp, Color(0xFFDDDDDD)))
        }

        items(postDTO.comments) { comment ->
            FeedElement(
                navigator,
                comment,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp),
                onDelete = { onCommentDelete(comment) }
            )
        }
        item {
            AuthOnly {
                Button(onClick = { navigator.navigate(NewPostScreenDestination()) }) {
                    Text(stringResource(R.string.add_comment), color = Color.White)
                }
            }
            AnonymousOnly {
                Text(stringResource(R.string.log_in_to_comment))
            }
        }
    }
}
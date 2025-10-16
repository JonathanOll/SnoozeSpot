package iut.fauryollivier.snoozespot.app.pages.feed.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import iut.fauryollivier.snoozespot.api.generated.model.Post
import iut.fauryollivier.snoozespot.app.pages.feed.components.FeedElement

@Composable
fun FeedElementDetailed(post: Post, modifier: Modifier = Modifier) {
    LazyColumn (modifier = modifier) {
        item {
            FeedElement(post)
            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).height(2.dp).border(1.dp, Color(0xFFDDDDDD)))
        }

        items(post.comments) { comment->
            FeedElement(
                comment,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
            )
        }
    }
}
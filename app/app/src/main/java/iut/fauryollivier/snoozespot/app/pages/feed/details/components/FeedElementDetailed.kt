package iut.fauryollivier.snoozespot.app.pages.feed.details.components

import androidx.compose.foundation.border
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
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.pages.feed.components.FeedElement

@Composable
fun FeedElementDetailed(navigator: DestinationsNavigator, postDTO: PostDTO, modifier: Modifier = Modifier) {
    LazyColumn (modifier = modifier) {
        item {
            FeedElement(navigator, postDTO)
            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).height(2.dp).border(1.dp, Color(0xFFDDDDDD)))
        }

        items(postDTO.comments) { comment->
            FeedElement(
                navigator,
                comment,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
            )
        }
    }
}
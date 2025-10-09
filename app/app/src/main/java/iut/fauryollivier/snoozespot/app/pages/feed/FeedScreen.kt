package iut.fauryollivier.snoozespot.app.pages.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.app.pages.feed.components.FeedElement
import iut.fauryollivier.snoozespot.generated.api.model.Post
import iut.fauryollivier.snoozespot.models.PostModel
import iut.fauryollivier.snoozespot.models.User


@Destination(start = true)
@Composable
fun FeedScreen(navigator: DestinationsNavigator, modifier: Modifier = Modifier, vm: FeedViewModel = viewModel()) {

    val posts: List<Post> by vm.posts.collectAsState()

    LaunchedEffect(true) {
        vm.fetchPosts()
    }

    LazyColumn {
        items(posts) {
            FeedElement(it)
        }
    }

}

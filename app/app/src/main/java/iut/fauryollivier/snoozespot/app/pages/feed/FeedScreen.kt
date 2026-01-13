package iut.fauryollivier.snoozespot.app.pages.feed

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.primaryContainerLight
import com.example.compose.primaryLight
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.FeedTopBar
import iut.fauryollivier.snoozespot.app.components.Loader
import iut.fauryollivier.snoozespot.app.pages.destinations.FeedDetailsScreenDestination
import iut.fauryollivier.snoozespot.app.pages.destinations.NewPostScreenDestination
import iut.fauryollivier.snoozespot.app.pages.feed.components.FeedElement
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.distinctUntilChanged


@Destination
@Composable
fun FeedScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    modifier: Modifier = Modifier,
    resultRecipient: ResultRecipient<NewPostScreenDestination, NewPostResult>,
    vm: FeedViewModel = viewModel()
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { FeedTopBar() }
        scaffoldController.showBottomBar.value = true
    }

    val context: Context = LocalContext.current

    LaunchedEffect(true) {
        vm.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast ->
                    Toast.makeText(context, context.getString(event.stringId), Toast.LENGTH_SHORT).show() } }
    }

    val state by vm.state.collectAsState()
    val listState = remember { LazyListState() }

    LazyFeed(
        lazyListState = listState,
        buffer = 1,
        onLoadMore = { vm.loadNextPosts() }
    )

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            vm.newPost(navigator, context, it.value)
        }
    }

    Box(modifier = modifier) {

        if(state.error != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(state.error!!.stringId))

                    Button(onClick = { vm.refresh() }) {
                        Text(stringResource(R.string.refresh), color = Color.White)
                    }
                }
            }
        } else {
            PullToRefreshBox(
                isRefreshing = state.isRefreshing,
                onRefresh = { vm.refresh() },
                modifier = Modifier.fillMaxSize()
            ) {
                if(state.isLoading && state.posts.isEmpty())
                    Loader()
                else {
                    LazyColumn (state = listState, modifier = Modifier.fillMaxSize()) {
                        items(state.posts) { post ->
                            FeedElement(
                                navigator,
                                post,
                                modifier = Modifier.clickable {
                                    navigator.navigate(FeedDetailsScreenDestination(post.id))
                                },
                                onLike = { vm.likePost(it) },
                                onDelete = { vm.deletePost(it) }
                            )
                        }
                    }
                }
            }
        }


        FloatingActionButton(
            onClick = { navigator.navigate(NewPostScreenDestination(showMediaPicker = true)) },
            backgroundColor = primaryContainerLight,
            contentColor = primaryLight,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
        ) {
            Icon(Icons.Filled.Add, stringResource(R.string.new_post))
        }

    }

}

@Composable
fun LazyFeed(
    lazyListState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItems - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                onLoadMore()
            }
    }
}

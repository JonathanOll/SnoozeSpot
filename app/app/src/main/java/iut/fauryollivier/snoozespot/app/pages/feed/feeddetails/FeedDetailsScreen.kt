package iut.fauryollivier.snoozespot.app.pages.feed.feeddetails

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.BackTopBar
import iut.fauryollivier.snoozespot.app.destinations.NewPostScreenDestination
import iut.fauryollivier.snoozespot.app.pages.feed.feeddetails.components.FeedElementDetailed
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import iut.fauryollivier.snoozespot.utils.UiEvent

@Destination
@Composable
fun FeedDetailsScreen(
    navigator: DestinationsNavigator,
    postId: Int,
    scaffoldController: ScaffoldController,
    vm: FeedDetailsViewModel = viewModel(),
    resultRecipient: ResultRecipient<NewPostScreenDestination, NewPostResult>,
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { BackTopBar(navigator) }
        scaffoldController.showBottomBar.value = false
    }

    val context: Context = LocalContext.current

    LaunchedEffect(true) {
        vm.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast ->
                    Toast.makeText(context, context.getString(event.stringId), Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    val postDTO: PostDTO? by vm.state.collectAsState()
    val errorMessage: ErrorMessage? by vm.errorMessage.collectAsState()

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            vm.sendPostComment(it.value)
        }
    }

    LaunchedEffect(true) {
        vm.fetchPost(postId)
    }
    if (errorMessage != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                androidx.compose.material3.Text(stringResource(errorMessage!!.stringId))

                Button(onClick = { vm.fetchPost(postId) }) {
                    androidx.compose.material3.Text(
                        stringResource(R.string.refresh),
                        color = Color.White
                    )
                }
            }
        }
    } else {
        postDTO?.let {
            FeedElementDetailed(
                navigator,
                postDTO!!,
                onLike = { vm.likePost(it.id) },
                onDelete = { vm.deletePost(it.id, { navigator.navigateUp() }) },
                onCommentDelete = { vm.deleteComment(it.id) }
            )
        }
    }

}
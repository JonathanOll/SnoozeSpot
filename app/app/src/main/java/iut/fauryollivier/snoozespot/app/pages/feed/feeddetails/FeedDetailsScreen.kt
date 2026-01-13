package iut.fauryollivier.snoozespot.app.pages.feed.feeddetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.app.components.BackTopBar
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.destinations.NewPostScreenDestination
import iut.fauryollivier.snoozespot.app.pages.feed.FeedViewModel
import iut.fauryollivier.snoozespot.app.pages.feed.feeddetails.components.FeedElementDetailed
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.utils.ErrorMessage

@Destination
@Composable
fun FeedDetailsScreen(
    navigator: DestinationsNavigator,
    postId: Int,
    scaffoldController: ScaffoldController,
    vm: FeedDetailsViewModel = viewModel(),
    resultRecipient: ResultRecipient<NewPostScreenDestination, NewPostResult>,
){
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { BackTopBar(navigator) }
        scaffoldController.showBottomBar.value = false
    }

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            vm.sendPostComment(it.value)
        }
    }

    val postDTO: PostDTO? by vm.postDTO.collectAsState()
    val errorMessage: ErrorMessage? by vm.errorMessage.collectAsState()

    LaunchedEffect(true) {
        vm.fetchPost(postId)
    }
    if(errorMessage != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(errorMessage!!.stringId))
        }
    } else {
        if(postDTO != null)
            FeedElementDetailed(
                navigator,
                postDTO!!,
                likePost = { vm.likePost(postDTO!!.id) },
            )
    }

}
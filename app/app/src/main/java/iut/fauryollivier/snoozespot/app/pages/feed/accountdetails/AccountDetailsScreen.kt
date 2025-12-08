package iut.fauryollivier.snoozespot.app.pages.feed.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
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
import iut.fauryollivier.snoozespot.app.components.BackTopBar
import iut.fauryollivier.snoozespot.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.HorizontalLine
import iut.fauryollivier.snoozespot.app.pages.account.components.UserProfileCard
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import java.util.UUID

@Destination
@Composable
fun AccountDetailsScreen(navigator: DestinationsNavigator, userId: UUID, scaffoldController: ScaffoldController, vm: AccountDetailsViewModel = viewModel()){
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { BackTopBar(navigator) }
        scaffoldController.showBottomBar.value = false
    }

    val account by vm.account.collectAsState()
    val errorMessage: ErrorMessage? by vm.errorMessage.collectAsState()

    LaunchedEffect(true) {
        vm.fetchUser(userId)
    }

    if(errorMessage != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(errorMessage!!.stringId))
        }
    } else {
        account?.let {
            UserProfileCard(account!!)

            HorizontalLine()
        }
    }



    /*
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
            FeedElementDetailed(postDTO!!)
    }*/

}
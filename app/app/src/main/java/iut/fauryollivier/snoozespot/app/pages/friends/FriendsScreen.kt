package iut.fauryollivier.snoozespot.app.pages.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.AuthOnly
import iut.fauryollivier.snoozespot.app.components.DefaultTopBar
import iut.fauryollivier.snoozespot.app.pages.destinations.AccountDetailsScreenDestination
import iut.fauryollivier.snoozespot.app.pages.destinations.AccountDetailsScreenDestination.invoke
import iut.fauryollivier.snoozespot.app.pages.friends.components.FriendElement

@Destination
@Composable
fun FriendsScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    vm: FriendsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { DefaultTopBar() }
        scaffoldController.showBottomBar.value = true
    }

    val friends by vm.friends.collectAsState()

    AuthOnly {
        LaunchedEffect(true) {
            vm.fetchFriends()
        }
    }


    friends?.let {
        if (friends!!.isNotEmpty()) {
            LazyColumn (
                modifier = modifier.fillMaxWidth()
            ) {

                items(friends!!) {
                    FriendElement(
                        it,
                            modifier = Modifier.clickable {
                                navigator.navigate(
                                    AccountDetailsScreenDestination(
                                        it.uuid
                                    )
                                )
                            }
                        )
                }

            }
        } else {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(stringResource(R.string.no_friends_yet))
            }
        }
    }
}
package iut.fauryollivier.snoozespot.app.pages.feed.accountdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.BackTopBar
import iut.fauryollivier.snoozespot.app.components.HorizontalLine
import iut.fauryollivier.snoozespot.app.pages.account.components.UserProfileCard
import iut.fauryollivier.snoozespot.app.pages.feed.components.FeedElement
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import java.util.UUID

@Destination
@Composable
fun AccountDetailsScreen(
    navigator: DestinationsNavigator,
    userId: UUID,
    scaffoldController: ScaffoldController,
    vm: AccountDetailsViewModel = viewModel()
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { BackTopBar(navigator) }
        scaffoldController.showBottomBar.value = false
    }

    val account by vm.account.collectAsState()
    val errorMessage: ErrorMessage? by vm.errorMessage.collectAsState()

    LaunchedEffect(true) {
        vm.fetchUser(userId)
    }

    if (errorMessage != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(stringResource(errorMessage!!.stringId))

                Button(onClick = { vm.fetchUser(userId) }) {
                    Text(stringResource(R.string.refresh), color = Color.White)
                }
            }
        }
    } else {
        account?.let {
            LazyColumn {
                item {
                    UserProfileCard(account!!)
                }
                item {
                    HorizontalLine()
                }
                item {
                    Text(
                        stringResource(R.string.posts),
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                    )
                }
                items(account!!.posts) {
                    FeedElement(
                        navigator,
                        postDTO = it,
                        onLike = { },
                        onDelete = { },
                    )
                }
            }
        }
    }

}
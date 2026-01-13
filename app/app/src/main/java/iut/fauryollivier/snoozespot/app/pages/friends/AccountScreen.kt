package iut.fauryollivier.snoozespot.app.pages.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.components.DefaultTopBar
import iut.fauryollivier.snoozespot.app.ScaffoldController

@Destination
@Composable
fun FriendsScreen(navigator: DestinationsNavigator, scaffoldController: ScaffoldController, modifier: Modifier = Modifier) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { DefaultTopBar() }
        scaffoldController.showBottomBar.value = true
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            painterResource(R.drawable.construction),
            contentDescription = "construction"
        )

    }
}
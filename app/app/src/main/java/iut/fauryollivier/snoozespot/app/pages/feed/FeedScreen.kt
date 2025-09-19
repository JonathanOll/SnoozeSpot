package iut.fauryollivier.snoozespot.app.pages.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.app.components.BottomBar
import iut.fauryollivier.snoozespot.app.pages.feed.components.FeedElement
import iut.fauryollivier.snoozespot.models.Post
import iut.fauryollivier.snoozespot.models.User


@Destination(start = true)
@Composable
fun FeedScreen(navigator: DestinationsNavigator, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 1..10)
            FeedElement(
                Post(User("Jonatan"),
                "Nice snooze!",
                    listOf(
                        "https://upload.wikimedia.org/wikipedia/commons/6/60/Leonhard_Euler_2.jpg",
                        "https://upload.wikimedia.org/wikipedia/commons/6/60/Leonhard_Euler_2.jpg",
                        "https://upload.wikimedia.org/wikipedia/commons/6/60/Leonhard_Euler_2.jpg"
                    )
                ))
    }

}
package iut.fauryollivier.snoozespot.app.components

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose.SnoozeSpotTheme
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.pages.destinations.AccountScreenDestination
import iut.fauryollivier.snoozespot.app.pages.destinations.FeedScreenDestination
import iut.fauryollivier.snoozespot.app.pages.destinations.FriendsScreenDestination
import iut.fauryollivier.snoozespot.app.pages.destinations.MapScreenDestination

@Composable
fun BottomBar(navController: NavHostController) {

    val currentDestination = navController.currentDestinationAsState()

    val icons = listOf(
        Icons.Outlined.ChatBubbleOutline,
        Icons.Outlined.Map,
        Icons.Outlined.Group,
        Icons.Outlined.AccountCircle
    )

    val invertedIcons = listOf(
        Icons.Filled.ChatBubble, Icons.Filled.Map, Icons.Filled.Group, Icons.Filled.AccountCircle
    )

    val destinations = listOf(
        FeedScreenDestination,
        MapScreenDestination,
        FriendsScreenDestination,
        AccountScreenDestination,
    )


    BottomAppBar(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = lightColorScheme().primaryContainer,
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                for (index in icons.indices) {
                    if (index != 0) Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                navController.navigate(destinations[index].route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                            .size(64.dp)) {

                        androidx.compose.animation.AnimatedVisibility(
                            visible = currentDestination.value?.route == destinations[index].route,
                            enter = scaleIn(),
                            exit = scaleOut(),
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.selected),
                                contentDescription = stringResource(R.string.navbar_selector),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Icon(
                            (if (currentDestination.value?.route == destinations[index].route)
                                invertedIcons
                            else
                                icons)[index],
                            contentDescription = stringResource(R.string.icon_n, index),
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SnoozeSpotTheme {
    }
}
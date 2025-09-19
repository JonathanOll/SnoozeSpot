package iut.fauryollivier.snoozespot.app.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.SnoozeSpotTheme
import iut.fauryollivier.snoozespot.R

@Composable
fun BottomBar(selectedIndex: Int = -1) {

    var selectedIndex by remember { mutableIntStateOf(selectedIndex) }

    val icons = listOf(
        Icons.Outlined.ChatBubbleOutline,
        Icons.Outlined.Map,
        Icons.Outlined.Group,
        Icons.Outlined.AccountCircle
    )

    val invertedIcons = listOf(
        Icons.Filled.ChatBubble, Icons.Filled.Map, Icons.Filled.Group, Icons.Filled.AccountCircle
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

                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .clickable { selectedIndex = index }
                        .size(64.dp)) {
                        if (index == selectedIndex) Image(
                            painter = painterResource(R.drawable.selected),
                            contentDescription = "Navbar selector",
                            modifier = Modifier.align(Alignment.Center)
                        )

                        Icon(
                            (if (index == selectedIndex) invertedIcons else icons)[index],
                            contentDescription = "Feed icon $index",
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
        BottomBar()
    }
}
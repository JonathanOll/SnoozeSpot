package iut.fauryollivier.snoozespot.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    TopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = lightColorScheme().primaryContainer,
            titleContentColor = lightColorScheme().onBackground
        )
    )
}

@Composable
fun BackTopBar(navigator: DestinationsNavigator) {
    TopBar {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .padding(end = 4.dp)
                .size(28.dp)
                .clickable {
                    navigator.popBackStack()
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentBackTopBar(navigator: DestinationsNavigator, rightElement: @Composable () -> Unit = {}) {
    TopAppBar(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            ),
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.White,  // Les icônes en blanc
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .size(28.dp)
                        .clickable {
                            navigator.popBackStack()
                        }
                )

                rightElement()
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent, // Important pour que le dégradé soit visible
            titleContentColor = Color.White
        ),
    )
}


@Composable
fun DefaultTopBar() {
    TopBar {
        Row(
            modifier = Modifier.fillMaxWidth()
                        .padding(end = 4.dp)
                        .height(28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(R.drawable.snoozespot), contentDescription = null
            )
        }
    }
}

@Composable
fun FeedTopBar() {
    TopBar {
        Icon(
            imageVector = Icons.Default.Language,
            contentDescription = stringResource(R.string.global),
            modifier = Modifier
                .padding(end = 4.dp)
                .size(28.dp)
        )
        Text(stringResource(R.string.global))
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = stringResource(R.string.others)
        )
    }
}

@Composable
fun PlusTopBar(onClick: () -> Unit, leftContent: @Composable () -> Unit = {}) {
    TopBar {
        Row(
            modifier = Modifier.fillMaxWidth().clickable(onClick=onClick),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            leftContent()

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(28.dp)
            )
        }
    }
}
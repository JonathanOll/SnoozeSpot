package iut.fauryollivier.snoozespot.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(content: @Composable () -> Unit = {}) {

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

@Composable
fun DefaultTopBar() {
    TopBar {
        Icon(
            imageVector = Icons.Default.Language,
            contentDescription = stringResource(R.string.global),
            modifier = Modifier
                .padding(end = 4.dp)
                .size(28.dp)
        )
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
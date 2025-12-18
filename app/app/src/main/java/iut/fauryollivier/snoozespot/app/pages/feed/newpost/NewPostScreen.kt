package iut.fauryollivier.snoozespot.app.pages.feed.newpost

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.primaryContainerLight
import com.example.compose.primaryLight
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.components.BackTopBar
import iut.fauryollivier.snoozespot.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.EditableStarRating
import iut.fauryollivier.snoozespot.app.components.ImagePicker
import java.io.Serializable

data class NewPostResult(
    val content: String,
    val uris: List<String>,
    val rating: Int
) : Serializable

@Destination
@Composable
fun NewPostScreen(
    navigator: DestinationsNavigator,
    showMediaPicker: Boolean = false,
    showGradePicker: Boolean = false,
    scaffoldController: ScaffoldController,
    resultBackNavigator: ResultBackNavigator<NewPostResult>
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { BackTopBar(navigator) }
        scaffoldController.showBottomBar.value = false
    }

    val pictures = remember { mutableStateListOf<Uri>() }
    val rating = remember { mutableIntStateOf(3) }
    var text by remember { mutableStateOf("") }

    Box (modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showGradePicker) {
                Text(stringResource(R.string.rating))
                EditableStarRating(rating)
            }

            if (showMediaPicker)
                ImagePicker(pictures)

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(R.string.post_content)) },
                modifier = Modifier.fillMaxSize()
            )
        }

        FloatingActionButton(
            onClick = {
                resultBackNavigator.navigateBack(
                    NewPostResult(
                        text,
                        pictures.map { it.toString() }.toList(),
                        rating.intValue
                    )
                ) },
            backgroundColor = primaryContainerLight,
            contentColor = primaryLight,
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 16.dp, bottom = 16.dp)
        ) {
            Icon(Icons.Filled.Check, stringResource(R.string.post))
        }
    }
}


package iut.fauryollivier.snoozespot.app.pages.map.newspot

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose.primaryContainerLight
import com.example.compose.primaryLight
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.BackTopBar
import iut.fauryollivier.snoozespot.app.components.ImagePicker
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import java.io.Serializable

data class NewSpotResult(
    val name: String,
    val description: String,
    val location: LatLng,
    val uris: List<String>
) : Serializable

@Destination
@Composable
fun NewSpotScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    resultBackNavigator: ResultBackNavigator<NewSpotResult>
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { BackTopBar(navigator) }
        scaffoldController.showBottomBar.value = false
    }

    val bouvent = LatLng(46.202, 5.228) // TODO: derniere position connue de l'utilisateur
    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bouvent, 14f)
    }

    val pictures = remember { mutableStateListOf<String>() }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box (modifier = Modifier.fillMaxSize()) {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ImagePicker(pictures)

            Text(stringResource(R.string.details))

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.name)) },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.description)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(stringResource(R.string.place))

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                MapView(cameraPosition)
            }
        }

        FloatingActionButton(
            onClick = {
                resultBackNavigator.navigateBack(
                    NewSpotResult(
                        name,
                        description,
                        cameraPosition.position.target,
                        pictures.map { it.toString() }.toList()
                    )
                )
            },
            backgroundColor = primaryContainerLight,
            contentColor = primaryLight,
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 16.dp, bottom = 16.dp)
        ) {
            Icon(Icons.Filled.Check, stringResource(R.string.post))
        }

    }

}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapView(cameraPosition: CameraPositionState) {
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        cameraPositionState = cameraPosition
    ) {
        Marker(
            state = MarkerState(position = cameraPosition.position.target),
            title = "Parc de Loisirs de Bouvent"
        )
    }
}
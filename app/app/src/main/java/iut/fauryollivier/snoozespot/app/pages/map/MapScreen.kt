package iut.fauryollivier.snoozespot.app.pages.map

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.components.DefaultTopBar
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.BottomBar
import iut.fauryollivier.snoozespot.app.components.PlusTopBar
import iut.fauryollivier.snoozespot.app.destinations.FeedDetailsScreenDestination
import iut.fauryollivier.snoozespot.app.destinations.FeedScreenDestination
import iut.fauryollivier.snoozespot.app.destinations.NewPostScreenDestination
import iut.fauryollivier.snoozespot.app.destinations.NewSpotScreenDestination
import iut.fauryollivier.snoozespot.app.destinations.OfflineSpotListScreenDestination
import iut.fauryollivier.snoozespot.app.destinations.SpotDetailsScreenDestination
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.app.pages.map.newspot.NewSpotResult
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun MapScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    modifier: Modifier = Modifier,
    resultRecipient: ResultRecipient<NewSpotScreenDestination, NewSpotResult>,
    vm: MapViewModel = viewModel()
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { PlusTopBar (
            {
                navigator.navigate(
                    NewSpotScreenDestination
                )
            },
            {
                Icon(
                    Icons.Default.Bookmarks,
                    contentDescription = stringResource(R.string.save_offline),
                    modifier = Modifier
                        .padding(4.dp)
                        .size(28.dp)
                        .clickable {
                            navigator.navigate(OfflineSpotListScreenDestination)
                        }
                )
            })
        }
        scaffoldController.showBottomBar.value = true
    }

    val context: Context = LocalContext.current

    LaunchedEffect(true) {
        vm.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast ->
                    Toast.makeText(context, context.getString(event.stringId), Toast.LENGTH_SHORT).show() } }
    }

    val state by vm.screenState.collectAsState()

    val bourg = LatLng(46.2, 5.216667)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bourg, 10f)
    }

    val currentCameraPosition = rememberUpdatedState(cameraPositionState.position)

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            vm.newSpot(it.value, context)
        }
    }

    LaunchedEffect(currentCameraPosition.value) {
        snapshotFlow { currentCameraPosition.value }
            .debounce(300)
            .collect {
                val bounds = cameraPositionState.projection?.visibleRegion?.latLngBounds
                if (bounds != null) {
                    vm.getSpots(bounds)
                }
            }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        state.spots.forEach { spot ->
            Marker(
                state = MarkerState(position = LatLng(spot.latitude, spot.longitude)),
                onClick = {
                    navigator.navigate(SpotDetailsScreenDestination(spot.id))
                    true
                }
            )
        }
    }
}
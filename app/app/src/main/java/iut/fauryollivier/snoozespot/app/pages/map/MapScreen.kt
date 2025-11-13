package iut.fauryollivier.snoozespot.app.pages.map

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.components.DefaultTopBar
import iut.fauryollivier.snoozespot.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.BottomBar
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun MapScreen(navigator: DestinationsNavigator, scaffoldController: ScaffoldController, modifier: Modifier = Modifier, vm: MapViewModel = viewModel()) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { DefaultTopBar() }
        scaffoldController.showBottomBar.value = true
    }

    val state by vm.screenState.collectAsState()

    val bourg = LatLng(46.2, 5.216667)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bourg, 10f)
    }

    val currentCameraPosition = rememberUpdatedState(cameraPositionState.position)

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
        state.spots.forEach {
            Marker(
                state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                title = it.name,
                snippet = it.description,
                onClick = {
                    Log.d("jonathan", "")
                    true
                }
            )
        }
    }
}
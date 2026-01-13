package iut.fauryollivier.snoozespot.app.pages.map.offlinelist

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.BackTopBar
import iut.fauryollivier.snoozespot.app.components.PlusTopBar
import iut.fauryollivier.snoozespot.app.components.StarRating
import iut.fauryollivier.snoozespot.app.destinations.NewSpotScreenDestination
import iut.fauryollivier.snoozespot.app.destinations.SpotDetailsScreenDestination
import iut.fauryollivier.snoozespot.app.destinations.SpotDetailsScreenDestination.invoke
import iut.fauryollivier.snoozespot.utils.UiEvent

@Composable
@Destination
fun OfflineSpotListScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    vm: OfflineSpotListViewModel = viewModel()
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { BackTopBar(navigator) }
        scaffoldController.showBottomBar.value = false
    }

    val context: Context = LocalContext.current

    LaunchedEffect(true) {
        vm.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast ->
                    Toast.makeText(context, context.getString(event.stringId), Toast.LENGTH_SHORT).show() } }
    }

    LaunchedEffect(true) {
        vm.fetchData()
    }

    val spots by vm.spots.collectAsState()

    LazyColumn {
        items(spots) { spot->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navigator.navigate(SpotDetailsScreenDestination(spotData = spot))
                    }
            ) {
                Text(spot.name)
                StarRating(spot.rating ?: 0f)
            }
        }
    }
}
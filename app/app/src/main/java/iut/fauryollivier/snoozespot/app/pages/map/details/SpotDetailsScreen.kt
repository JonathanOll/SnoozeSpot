package iut.fauryollivier.snoozespot.app.pages.map.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.components.AnonymousOnly
import iut.fauryollivier.snoozespot.app.components.AuthOnly
import iut.fauryollivier.snoozespot.app.components.ExpandableImage
import iut.fauryollivier.snoozespot.app.components.ExpandableImageWithDownload
import iut.fauryollivier.snoozespot.app.components.HorizontalLine
import iut.fauryollivier.snoozespot.app.components.StarRating
import iut.fauryollivier.snoozespot.app.components.TransparentBackTopBar
import iut.fauryollivier.snoozespot.app.destinations.NewPostScreenDestination
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.app.pages.map.details.components.SpotComment
import iut.fauryollivier.snoozespot.utils.ErrorMessage

@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun SpotDetailsScreen(
    navigator: DestinationsNavigator,
    spotId: Int? = null,
    spotData: SpotDTO? = null,
    scaffoldController: ScaffoldController,
    vm: SpotDetailsViewModel = viewModel(),
    resultRecipient: ResultRecipient<NewPostScreenDestination, NewPostResult>,
){
    LaunchedEffect(true) {
        scaffoldController.topBar.value = {  }
        scaffoldController.showBottomBar.value = false
    }

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            vm.sendSpotComment(it.value)
        }
    }

    val spot: SpotDTO? by vm.spot.collectAsState()
    val errorMessage: ErrorMessage? by vm.errorMessage.collectAsState()
    val saved: Boolean by vm.offlineSaved.collectAsState()

    val mapPositionState = rememberCameraPositionState()
    LaunchedEffect(spot) {
        if (spot != null) {
            mapPositionState.position = CameraPosition.fromLatLngZoom(
                LatLng(spot!!.latitude, spot!!.longitude),
                15f
            )
        }
    }

    LaunchedEffect(true) {
        if (spotId != null)
            vm.fetchSpot(spotId)
        else
            vm.setSpot(spotData!!)
    }
    Box {
        Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            if(spot != null) {
                if (!spot!!.pictures.isEmpty()) {
                    ExpandableImageWithDownload(
                        imageUri = NetworkDataSource.BASE_URL + spot!!.pictures[0].path,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
                else
                    Image(
                        painter = painterResource(R.drawable.lobster),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        contentScale = ContentScale.Crop
                    )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        spot!!.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 36.sp,
                        lineHeight = 36.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalLine(1.dp, Color(0xFFE6E6E6))
                    Text(
                        spot!!.description,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    HorizontalLine(1.dp, Color(0xFFE6E6E6))

                    Row(
                        modifier = Modifier.height(50.dp).padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.LocationOn, stringResource(R.string.post))
                        Text("Emplacement")
                    }

                    GoogleMap(
                        modifier = Modifier.fillMaxWidth().aspectRatio(1/0.8f),
                        cameraPositionState = mapPositionState,
                        uiSettings = MapUiSettings(
                            compassEnabled = false,
                            indoorLevelPickerEnabled = false,
                            mapToolbarEnabled = false,
                            myLocationButtonEnabled = false,
                            rotationGesturesEnabled = false,
                            scrollGesturesEnabled = false,
                            scrollGesturesEnabledDuringRotateOrZoom = false,
                            tiltGesturesEnabled = false,
                            zoomControlsEnabled = false,
                            zoomGesturesEnabled = false
                        )
                    ) {
                        Marker(
                            state = MarkerState(position = LatLng(spot!!.latitude, spot!!.longitude))
                        )
                    }

                    Row(
                        modifier = Modifier.height(50.dp).padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.RateReview, stringResource(R.string.post))
                        Text("Avis")
                    }

                    if (spot?.rating != null) {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            StarRating(spot?.rating ?: 0f)
                            Text("%.1f étoile(s)".format(spot?.rating ?: 0f))
                        }
                        HorizontalLine(1.dp, Color(0xFFE6E6E6))
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            spot!!.comments.forEach { comment ->
                                SpotComment(navigator, comment)
                            }
                            AuthOnly {
                                Button(onClick = { navigator.navigate(NewPostScreenDestination(showGradePicker = true)) }) {
                                    Text(
                                        stringResource(R.string.add_comment),
                                        color = Color.White
                                    )
                                }
                            }
                            AnonymousOnly {
                                Text(stringResource(R.string.log_in_to_comment))
                            }
                        }

                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth().height(150.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(stringResource(R.string.no_review))
                            AuthOnly {
                                Button(onClick = { navigator.navigate(NewPostScreenDestination(showGradePicker = true)) }) {
                                    Text(stringResource(R.string.add_comment), color = Color.White)
                                }
                            }
                            AnonymousOnly {
                                Text(stringResource(R.string.log_in_to_comment))
                            }
                        }
                    }
                }
            }
        }
        TransparentBackTopBar(navigator) { // on doit le mettre ici et pas de le scaffold pour que les éléments puissent se placer en dessous
            Icon(
                if (saved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                tint = Color.White,
                contentDescription = stringResource(R.string.save_offline),
                modifier = Modifier
                    .padding(4.dp)
                    .size(28.dp)
                    .clickable {
                    vm.saveOffline()
                }
            )
        }
    }

}
package iut.fauryollivier.snoozespot.app.pages.map

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.app.pages.map.newspot.NewSpotResult
import iut.fauryollivier.snoozespot.repositories.SpotsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {

    data class ScreenState(
        val spots: List<SpotDTO> = emptyList()
    )

    private val _state = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _state.asStateFlow()

    var map: GoogleMap? = null

    fun getSpots(bounds: LatLngBounds) {
        val topLeft = LatLng(bounds.northeast.latitude, bounds.southwest.longitude)
        val bottomRight = LatLng(bounds.southwest.latitude, bounds.northeast.longitude)

        viewModelScope.launch {
            val result = SpotsRepository.getSpotsZone(topLeft, bottomRight)

            if (result.isSuccessful && result.body() != null) {
                _state.update {
                    it.copy(spots = result.body()!!)
                }
            }
        }
    }

    fun newSpot(data: NewSpotResult, context: Context) {
        viewModelScope.launch {
            SpotsRepository.createSpot(
                context,
                data.name,
                data.description,
                data.location.latitude,
                data.location.longitude,
                data.uris
            )
        }
    }

}
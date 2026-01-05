package iut.fauryollivier.snoozespot.app.pages.map

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import iut.fauryollivier.snoozespot.repositories.SpotsRepository
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MapViewModel : ViewModel() {

    data class ScreenState(
        val spots: List<SpotDTO> = emptyList()
    )

    private val _state = MutableStateFlow(ScreenState())
    val screenState: StateFlow<ScreenState> = _state.asStateFlow()

    var map: GoogleMap? = null

    suspend fun getSpots(bounds: LatLngBounds) {
        val topLeft = LatLng(bounds.northeast.latitude, bounds.southwest.longitude)
        val bottomRight = LatLng(bounds.southwest.latitude, bounds.northeast.longitude)

        val result = SpotsRepository.getSpotsZone(topLeft, bottomRight)

        if (result.isSuccessful && result.body() != null) {
            _state.update {
                it.copy(spots = result.body()!!)
            }
        }
    }

}
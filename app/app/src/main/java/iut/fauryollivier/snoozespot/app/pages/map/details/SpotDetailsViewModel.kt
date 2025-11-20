package iut.fauryollivier.snoozespot.app.pages.map.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.api.data.repositories.SpotsRepository
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpotDetailsViewModel: ViewModel() {

    private val spotState: MutableStateFlow<SpotDTO?> = MutableStateFlow(null)
    val spot: StateFlow<SpotDTO?> = spotState.asStateFlow()
    private var errorMessageState = MutableStateFlow<ErrorMessage?>(null)
    var errorMessage: StateFlow<ErrorMessage?> = errorMessageState.asStateFlow()

    fun fetchSpot(spotId: Int) {
        viewModelScope.launch {
            val spot = SpotsRepository.getSpot(spotId)
            if(spot.isSuccessful && spot.body() != null)
                spotState.value = spot.body()!!
            else
                errorMessageState.value = ErrorMessage.COULD_NOT_FETCH_ERROR
        }
    }
}
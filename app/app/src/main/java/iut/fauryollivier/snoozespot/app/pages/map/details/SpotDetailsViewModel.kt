package iut.fauryollivier.snoozespot.app.pages.map.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.repositories.SpotsRepository
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.app.pages.feed.newpost.NewPostResult
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpotDetailsViewModel: ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val spotState: MutableStateFlow<SpotDTO?> = MutableStateFlow(null)
    val spot: StateFlow<SpotDTO?> = spotState.asStateFlow()
    private var errorMessageState = MutableStateFlow<ErrorMessage?>(null)
    var errorMessage: StateFlow<ErrorMessage?> = errorMessageState.asStateFlow()
    private val _offlineSaved: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val offlineSaved: StateFlow<Boolean> = _offlineSaved.asStateFlow()

    fun fetchSpot(spotId: Int) {
        viewModelScope.launch {
            val spot = SpotsRepository.getSpot(spotId)
            if(spot.isSuccessful && spot.body() != null) {
                spotState.value = spot.body()!!
                _offlineSaved.value = SpotsRepository.isSavedOffline(spotState.value!!.id)
            }
            else
                errorMessageState.value = ErrorMessage.COULD_NOT_FETCH_ERROR
        }
    }

    fun setSpot(spotData: SpotDTO) {
        spotState.value = spotData
    }

    fun sendSpotComment(data: NewPostResult) {
        viewModelScope.launch {
            val result = SpotsRepository.createSpotComment(spotState.value!!.id, data.content, data.rating)
            if (!result.isSuccessful) {
                _events.emit(UiEvent.ShowToast(R.string.failed_to_comment))
            }
        }
    }

    fun saveOffline() {
        if (spot.value == null)
            return

        viewModelScope.launch {
            if (offlineSaved.value)
                SpotsRepository.removeOffline(spot.value!!.id)
            else
                SpotsRepository.saveOffline(spot.value!!)
            _offlineSaved.value = SpotsRepository.isSavedOffline(spotState.value!!.id)
        }
    }
}
package iut.fauryollivier.snoozespot.app.pages.map.offlinelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.repositories.SpotsRepository
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OfflineSpotListViewModel : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _spots: MutableStateFlow<List<SpotDTO>> = MutableStateFlow(emptyList())
    val spots: StateFlow<List<SpotDTO>> = _spots.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            var response = SpotsRepository.getSpotsOffline()
            if (response.isSuccessful)
                _spots.value = response.body()!!
            else
                _events.emit(UiEvent.ShowToast(R.string.failed_to_fetch_data))
        }
    }
}
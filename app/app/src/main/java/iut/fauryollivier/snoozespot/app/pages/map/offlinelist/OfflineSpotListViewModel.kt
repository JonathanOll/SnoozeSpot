package iut.fauryollivier.snoozespot.app.pages.map.offlinelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.repositories.SpotsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OfflineSpotListViewModel : ViewModel() {

    private val _spots: MutableStateFlow<List<SpotDTO>> = MutableStateFlow(emptyList())
    val spots: StateFlow<List<SpotDTO>> = _spots.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            var response = SpotsRepository.getSpotsOffline()
            if (response.isSuccessful)
                _spots.value = response.body()!!
        }
    }
}
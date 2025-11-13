package iut.fauryollivier.snoozespot.app.components.splashscreen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _loaded = MutableStateFlow<Boolean>(false)
    val loaded: StateFlow<Boolean> = _loaded.asStateFlow()

    fun load() {
        viewModelScope.launch {
            // TODO: charger le profil à partir du token, ...
            delay(500)
            _loaded.value = true
        }
    }

}
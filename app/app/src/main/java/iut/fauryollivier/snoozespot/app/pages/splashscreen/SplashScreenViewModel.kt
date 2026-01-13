package iut.fauryollivier.snoozespot.app.pages.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import iut.fauryollivier.snoozespot.repositories.UsersRepository
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _loaded = MutableStateFlow<Boolean>(false)
    val loaded: StateFlow<Boolean> = _loaded.asStateFlow()

    fun load(localStorage: LocalStorage) {
        viewModelScope.launch {
            val result = UsersRepository.getMe()
            if (result.isSuccessful) {
                localStorage.saveUser(result.body()!!)
            } else {
                if(localStorage.getUser.first() != null) {
                    _events.emit(UiEvent.ShowToast(R.string.logged_out))
                }

                localStorage.saveAuthToken(null)
                localStorage.saveUser(null)
            }

            _loaded.value = true
        }
    }

}
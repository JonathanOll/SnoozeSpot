package iut.fauryollivier.snoozespot.app.pages.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.UserAuthRequest
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import iut.fauryollivier.snoozespot.repositories.UsersRepository
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _email = MutableStateFlow("")
    var email = _email.asStateFlow()

    private val _username = MutableStateFlow("")
    var username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    fun onEmailChanged(new: String) {
        _email.update { new }
    }

    fun onUsernameChanged(new: String) {
        _username.update { new }
    }

    fun onPasswordChanged(new: String) {
        _password.update { new }
    }

    fun register(localStorage: LocalStorage, navigateUp: () -> Unit) {
        viewModelScope.launch {
            val response = UsersRepository.register(_username.value, _password.value, _email.value)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                localStorage.saveAuthToken(data.accessToken)
                localStorage.saveUser(data.user)
                navigateUp()
            } else {
                _events.emit(UiEvent.ShowToast(R.string.could_not_register))
            }
        }
    }

}
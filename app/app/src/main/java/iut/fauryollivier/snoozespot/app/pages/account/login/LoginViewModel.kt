package iut.fauryollivier.snoozespot.app.pages.account.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import iut.fauryollivier.snoozespot.repositories.UsersRepository
import iut.fauryollivier.snoozespot.utils.Toaster
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _username = MutableStateFlow("")
    var username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    var password = _password.asStateFlow()

    fun onUsernameChanged(new: String) {
        _username.update { new }
    }

    fun onPasswordChanged(new: String) {
        _password.update { new }
    }

    fun login(localStorage: LocalStorage, navigateUp: () -> Unit) {
        viewModelScope.launch {
            val response = UsersRepository.login(_username.value, _password.value)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                localStorage.saveAuthToken(data.accessToken)
                localStorage.saveUser(data.user)
                navigateUp()
            } else {
                Toaster.instance.toast(R.string.could_not_login)
            }
        }
    }

}
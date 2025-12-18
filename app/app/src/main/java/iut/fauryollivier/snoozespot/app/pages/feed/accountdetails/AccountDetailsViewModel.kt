package iut.fauryollivier.snoozespot.app.pages.feed.feeddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.api.data.repositories.UsersRepository
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AccountDetailsViewModel : ViewModel() {

    private val _account: MutableStateFlow<UserDTO?> = MutableStateFlow(null)
    val account: StateFlow<UserDTO?> = _account.asStateFlow()
    private var errorMessageState = MutableStateFlow<ErrorMessage?>(null)
    var errorMessage: StateFlow<ErrorMessage?> = errorMessageState.asStateFlow()

    fun fetchUser(id: UUID) {
        viewModelScope.launch {
            val account = UsersRepository.getUser(id)
            if(account.isSuccessful && account.body() != null)
                _account.value = account.body()!!
            else
                errorMessageState.value = ErrorMessage.COULD_NOT_FETCH_ERROR
        }
    }

}
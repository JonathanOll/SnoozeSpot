package iut.fauryollivier.snoozespot.app.pages.feed.accountdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import iut.fauryollivier.snoozespot.repositories.UsersRepository
import iut.fauryollivier.snoozespot.utils.ErrorMessage
import iut.fauryollivier.snoozespot.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class AccountDetailsViewModel : ViewModel() {

    private val _account: MutableStateFlow<UserDTO?> = MutableStateFlow(null)
    val account: StateFlow<UserDTO?> = _account.asStateFlow()

    private var _errorMessage = MutableStateFlow<ErrorMessage?>(null)
    var errorMessage: StateFlow<ErrorMessage?> = _errorMessage.asStateFlow()

    fun fetchUser(id: UUID) {
        _errorMessage.update { null }

        viewModelScope.launch {
            val account = UsersRepository.getUser(id)
            if (account.isSuccessful && account.body() != null)
                _account.value = account.body()!!
            else
                _errorMessage.value = ErrorMessage.COULD_NOT_FETCH_ERROR
        }
    }

    fun followUser(uuid: UUID) {
        viewModelScope.launch {
            val result = UsersRepository.followUser(uuid)
            if (result.isSuccessful) {
                _account.value = _account.value!!.copy(
                    followedByUser = true
                )
            } else {
                Toaster.instance.toast(R.string.could_not_follow)
            }
        }
    }

    fun unfollowUser(uuid: UUID) {
        viewModelScope.launch {
            val result = UsersRepository.unfollowUser(uuid)
            if (result.isSuccessful) {
                _account.value = _account.value!!.copy(
                    followedByUser = false
                )
            } else {
                Toaster.instance.toast(R.string.could_not_unfollow)
            }
        }
    }

}
package iut.fauryollivier.snoozespot.app.pages.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import iut.fauryollivier.snoozespot.app.pages.feed.FeedViewModel.ScreenState
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import iut.fauryollivier.snoozespot.repositories.UsersRepository
import iut.fauryollivier.snoozespot.utils.Toaster
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FriendsViewModel : ViewModel() {

    private val _friends = MutableStateFlow<List<UserDTO>?>(null)
    val friends: StateFlow<List<UserDTO>?> = _friends.asStateFlow()

    fun fetchFriends() {
        viewModelScope.launch {
            val result = UsersRepository.getFriends()
            if (result.isSuccessful)
                _friends.value = result.body()
            else
                Toaster.instance.toast(R.string.failed_to_fetch_data)
        }
    }

    fun unfollow(user: UserDTO) {
        viewModelScope.launch {
            val result = UsersRepository.unfollowUser(user.uuid)
            if (result.isSuccessful) {
                _friends.value = _friends.value!!.filter {
                    it.uuid != user.uuid
                }
            } else {
                Toaster.instance.toast(R.string.could_not_unfollow)
            }
        }
    }

}
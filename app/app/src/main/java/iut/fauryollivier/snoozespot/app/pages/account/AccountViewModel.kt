package iut.fauryollivier.snoozespot.app.pages.account

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import iut.fauryollivier.snoozespot.repositories.UsersRepository
import iut.fauryollivier.snoozespot.utils.Toaster
import iut.fauryollivier.snoozespot.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    fun logout(localStorage: LocalStorage) {
        viewModelScope.launch {
            localStorage.saveAuthToken(null)
            localStorage.saveUser(null)
            // TODO envoyer une requête à l'api pour invalider le token
        }
    }

    fun changeProfilePic(context: Context, uri: Uri) {
        viewModelScope.launch {
            val result = UsersRepository.changeProfilePic(context, uri.toString())
            if (result.isSuccessful) {
                Toaster.instance.toast(R.string.profile_picture_updated)
                LocalStorage(context).saveUser(result.body()!!)
            } else {
                Toaster.instance.toast(R.string.failed_to_change_profile_pic)
            }
        }
    }

}